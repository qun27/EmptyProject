package painter1024.emptyptoject.lib_android.util.storage.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件保存工具
 */

public class FileSaveUtil {

    /**
     * 文件保存
     * @param source 数据源
     * @param target 存储位置
     * @return 是否成功
     */
    public static boolean save(InputStream source, File target) {
        try {
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[1024];
                outputStream = new FileOutputStream(target);
                while (true) {
                    int read = source.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (source != null) {
                    source.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}
