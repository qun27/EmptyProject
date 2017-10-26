package painter1024.emptyptoject.lib_android.util.storage.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 文件操作工具
 */

public class FileOptionUtil {

    /***
     * 删除文件
     */
    public static boolean delete(File file)throws IOException {
        return file != null && file.delete();
    }

    /**
     * 复制文件
     * @param source 源文件
     * @param target 目标位置
     */
    public static void copy(File source, File target) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            fi = new FileInputStream(source);
            fo = new FileOutputStream(target);
            FileChannel in = fi.getChannel();//得到对应的文件通道
            FileChannel out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fo != null) fo.close();
                if (fi != null) fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
