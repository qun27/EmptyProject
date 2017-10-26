package painter1024.emptyproject.biz.data.boot.store.sp;

import painter1024.emptyproject.project.data.sp.SPClient;

/**
 * 版本码轻量级存储
 */

public class VersionCodeSP {
    // 版本号
    private static String KEY_VERSION_CODE = "KEY_VERSION_CODE";
    public static int getVersionCode() {
        return SPClient.sp().getInt(KEY_VERSION_CODE, 0);
    }
    public static void putVersionCode(int versionCode) {
        SPClient.putInt(KEY_VERSION_CODE, versionCode);
    }
}
