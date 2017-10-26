package painter1024.emptyproject.third.glide;

import com.bumptech.glide.Glide;

import java.io.File;

import painter1024.emptyptoject.lib.util.calculate.SizeUnitsConvertUtils;
import painter1024.emptyptoject.lib.util.format.SizeUnitsFormatUtils;
import painter1024.emptyproject.app.MyApplication;

/**
 * Glide工具类
 */

public class GlideUtil {

    /**
     * 清除磁盘缓存
     */
    public static void clearDiskCache(){
        Glide.get(MyApplication.getInstance()).clearDiskCache();
    }

    /**
     * 清除内存缓存
     */
    public static void clearMemory(){
       Glide.get(MyApplication.getInstance()).clearMemory();
    }

    /**
     * 获取Glide造成的缓存大小
     *
     * @return CacheSize
     */
    public static String getCacheSize() {
        try {
            long size = getFolderSize(Glide.getPhotoCacheDir(MyApplication.getInstance()));
            return SizeUnitsFormatUtils.formatFit(SizeUnitsConvertUtils.B, size, SizeUnitsConvertUtils.B, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取指定文件夹内所有文件大小的和
     *
     * @param file file
     * @return size
     * @throws Exception
     */
    private static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
}
