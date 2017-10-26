package painter1024.emptyproject.biz.data.boot.store.sp;

import painter1024.emptyproject.project.data.sp.SPClient;

/**
 * 缓存轻量存储
 */

public class BootImageCacheSP {
    /**
     * 启动页图片
     */
    private static String KEY_BOOT_IMAGE = "KEY_BOOT_IMAGE";
    public static String getBootImage() {
        return SPClient.sp().getString(KEY_BOOT_IMAGE, "");
    }
    public static void putBootImage(String splashImage) {
        SPClient.putString(KEY_BOOT_IMAGE, splashImage);
    }
}
