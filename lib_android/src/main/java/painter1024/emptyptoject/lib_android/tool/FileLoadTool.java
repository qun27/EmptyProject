package painter1024.emptyptoject.lib_android.tool;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 文件下载工具
 */
@SuppressWarnings("unused")
public class FileLoadTool {
	public static final String TAG = "FileLoadTool";

	private boolean DEBUG = false;

	private static final String CACHE_SUFFIXES = ".tmp";

	public static final int START = 0; //开始下载
	public static final int REFRESH = 1; //刷新进度
	public static final int END = 2; //下载完成
	public static final int ERROR = 3; //发生异常

	private String mUrl;
	private String mFullPath;
	private String mPath;
	private String mFileName;
	private boolean mDeleteExist = true;
	private boolean isCancel = false;

	private long mFileSize;

	private LoadListener mListener;

	public FileLoadTool(String url, String path, String fileName, LoadListener listener) {
		init(url, path, fileName, listener);
	}

	public FileLoadTool(String url, String fullPath, LoadListener listener){
		int index = fullPath.lastIndexOf(File.separator);
		String fileName = fullPath.substring(index+1);
		String path = fullPath.substring(0, index);
		init(url, path, fileName, listener);
	}

	private void init(String url, String path, String fileName, LoadListener listener){
		mUrl = url;
		mFileName = fileName;
		mPath = path;

		mFullPath = mPath + File.separator + mFileName;
		mListener = listener;
	}

	/**
	 * 删除已存在
	 */
	public void setDeleteExist(boolean deleteExist){
		mDeleteExist = deleteExist;
	}

	public void load(){
		new Thread(){
			public void run(){
				if(DEBUG) Log.e(TAG, "下载位置：" + mFullPath + " # 来源：" + mUrl);
				loadFile(mUrl, mFullPath, mDeleteExist);
			}
		}.start();
	}

	/**
	 * 停止下载
	 */
	public void cancel() {
		isCancel = true;
		if (mListener != null)
			mListener.onCancel();
		mListener = null;
	}


	private void loadFile(String url, String fullPath, boolean deleteExist){

		//如果存在，直接返回成功
		File file = new File(fullPath);
		if(file.exists() ){
			if(deleteExist){
				boolean success = file.delete();
				if(!success) Log.e(TAG, "exist target file delete failed");
			}else{
				sendMsg(END, fullPath);
				return;
			}
		}

		String cachePath = deleteCacheFile();

		InputStream is = null;
		FileOutputStream fos = null;

		try{
			URL myURL = new URL(url);
			URLConnection conn = myURL.openConnection();
			conn .setRequestProperty("Accept-Encoding", "identity");
			conn.connect();
			is = conn.getInputStream();
			mFileSize = conn.getContentLength();//根据响应获取文件大小
			if (mFileSize <= 0) {
				throw new RuntimeException("无法获知文件大小 ");
			}
			if (is == null) throw new RuntimeException("stream is null");
			fos = new FileOutputStream(cachePath);

			//把数据存入路径+文件名
			byte buf[] = new byte[1024 << 8];//加快下载速度
			mLoadSize = 0;

			sendMsg(START, null);
			do{
				if (isCancel) return;
				int numread = is.read(buf);
				if (numread == -1) break;
				fos.write(buf, 0, numread);
				mLoadSize += numread;
				if (isCancel) return;
				sendMsg(REFRESH, null);//更新进度条
			} while (true);
			boolean success = new File(cachePath).renameTo(new File(fullPath));//改名
			if(success)	sendMsg(END, fullPath);//通知下载完成
			else sendMsg(ERROR, "file rename fail!");

		}catch (Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				if (is != null) {
					is.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException ignored) {
			}
		}
	}

	/**
	 * 删除临时缓存文件
	 */
	public String deleteCacheFile() {
		File file;
		String cachePath = mFullPath + CACHE_SUFFIXES;
		//缓存文件，如果存在，直接删除
		file = new File(cachePath);
		if (file.exists()) {
			boolean success = file.delete();
			if (!success) Log.e(TAG, "exist tmp file delete failed");
		}
		return cachePath;
	}

	private void sendMsg(int flag, Object obj)
	{
		Message msg = new Message();
		msg.what = flag;
		msg.obj = obj;
		handler.sendMessage(msg);
	}

	private long mLoadSize;

	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{//定义一个Handler，用于处理下载线程与UI间通讯
			if (!Thread.currentThread().isInterrupted())
			{
				switch (msg.what)
				{
					case START:
						if(mListener!=null) mListener.onStart(mFileSize);
						if(DEBUG) Log.e(TAG, "load# size= " + mFileSize);
					case REFRESH:
						if(mListener!=null) mListener.onRefresh(mLoadSize, mFileSize);

						int result = (int) (mLoadSize * 100 / mFileSize);
						if(DEBUG) Log.e(TAG, "load#" + result +"%");
						break;
					case END:
						if(mListener!=null) mListener.onEnd((String)msg.obj);
						if(DEBUG) Log.e(TAG, "load#" + "end");
						break;
					case ERROR:
						if(mListener!=null) mListener.onError((String)msg.obj);
						if(DEBUG) Log.e(TAG, "load#" + "error");
						break;
				}
			}
			super.handleMessage(msg);
		}
	};

	public String getPath() {
		return mPath;
	}

	public String getFileName() {
		return mFileName;
	}

	public void setLoadListener(LoadListener listener){
		mListener = listener;
	}

	public interface LoadListener{
		void onStart(long fileSize);
		void onRefresh(long progress, long fileSize);
		void onEnd(String file);
		void onError(String msg);
		void onCancel();
	}

}

