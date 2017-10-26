package painter1024.emptyptoject.lib_android.util.storage.file;

import java.io.File;

/**
 * 文件路径工具
 */
public class FilePathUtil {

    /***
     * 获取文件后缀
     * @param path 文件路径或者文件名
     * @return 返回文件后缀
     */
    public static String getFileSuffix(String path) {
        if ((path != null) && (path.length() > 0)) {
            String fileName = getFileName(path);
            int dot = fileName.lastIndexOf('.');
            if (dot >-1 && dot < (fileName.length() - 1) ) {
                return fileName.substring(dot + 1);
            }
        }
        return "";
    }

    /***
     * 获取文件文件名
     * @param path 文正路径
     * @return 返回文件名
     */
    public static String getFileName(String path) {
        if(path==null) return "";
        int separatorIndex = path.lastIndexOf(File.separator);
        return (separatorIndex < 0) ? path : path.substring(separatorIndex + 1, path.length());
    }

    /**
     * 获取文件目录
     * @param path 文件路径
     * @return 返回文件目录
     */
    public static String getDir(String path){
        int index = path.lastIndexOf(File.separator);
        String dir = path.substring(0, index);
        return dir;
    }

}
