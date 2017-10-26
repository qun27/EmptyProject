package painter1024.emptyproject.project.data.file;

/**
 * 文件路径常量
 */

import android.content.Context;
import android.os.Environment;

import painter1024.emptyptoject.lib_android.util.app.AppUtil;
import painter1024.emptyproject.app.MyApplication;

/**
 * 文件路径常量
 */
public class FilePath {

    public static final String DIR_CACHE;

    public static final String DIR_CACHE_APK;
    public static final String DIR_CACHE_VOICE;
    public static final String DIR_CACHE_VIDEO;
    public static final String DIR_CACHE_ATTACHMENT;

    public static final String DIR_CACHE_IMAGE;
    public static final String DIR_CACHE_CAMERA_PHOTO;
    public static final String DIR_CACHE_THUMB;
    public static final String DIR_CACHE_IMAGE_COMPRESSION;

    public static final String DIR_CACHE_ERROR_LOG;
    public static final String DIR_CACHE_LOG;

    public static final String FILE_APK;
    public static final String FILE_LOG;
    public static final String FILE_NDK_LOG;

    /**
     * 内部缓存目录
     */
    public static final String DIR_CACHE_INTERNAL;


    static {
        Context context = MyApplication.getInstance();
        String packageName = context.getPackageName();
        DIR_CACHE = Environment.getExternalStorageDirectory() + "/" + packageName + "/cache/";

        DIR_CACHE_APK               = DIR_CACHE + "apk";
        DIR_CACHE_VOICE             = DIR_CACHE + "voice";
        DIR_CACHE_VIDEO             = DIR_CACHE + "video";
        DIR_CACHE_ATTACHMENT        = DIR_CACHE + "attachment";

        DIR_CACHE_IMAGE             = DIR_CACHE + "images";
        DIR_CACHE_CAMERA_PHOTO      = DIR_CACHE + "picture";
        DIR_CACHE_THUMB             = DIR_CACHE + "thumb";
        DIR_CACHE_IMAGE_COMPRESSION = DIR_CACHE + "compression";

        DIR_CACHE_ERROR_LOG     = DIR_CACHE + "errorlog";
        DIR_CACHE_LOG           = DIR_CACHE + "log";

        FILE_APK        = DIR_CACHE_APK + "/" + AppUtil.getAppName(context) + ".apk";
        FILE_NDK_LOG    = DIR_CACHE_LOG + "/ndk.txt";
        FILE_LOG        = DIR_CACHE_LOG + "/log.txt";

        DIR_CACHE_INTERNAL = context.getFilesDir().getPath()+ "/" + packageName + "/cache";
    }

}
