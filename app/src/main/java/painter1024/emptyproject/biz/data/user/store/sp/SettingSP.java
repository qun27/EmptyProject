package painter1024.emptyproject.biz.data.user.store.sp;


import painter1024.emptyproject.project.data.sp.SPClient;

/**
 * 设置轻量储存
 * 常量命名方式： 类型（KEY） + 模块 + 描述
 * 存放方式，按照属性区分
 */
public class SettingSP {

    /**
     * 仅WiFi下载
     */
    private static final String KEY_USER_WIFI_DOWNLOAD = "KEY_USER_WIFI_DOWNLOAD";
    public static boolean getWifiDownload() {
        return SPClient.sp().getBoolean(KEY_USER_WIFI_DOWNLOAD, true);
    }
    public static void putWifiDownload(boolean wifiDownload) {
        SPClient.putBoolean(KEY_USER_WIFI_DOWNLOAD, wifiDownload);
    }

    /**
     * 仅WiFi观看
     */
    private static final String KEY_USER_WIFI_WATCH = "KEY_USER_WIFI_WATCH";
    public static boolean getWifiWatch() {
        return SPClient.sp().getBoolean(KEY_USER_WIFI_WATCH, true);
    }
    public static void putWifiWatch(boolean wifiWatch) {
        SPClient.putBoolean(KEY_USER_WIFI_WATCH, wifiWatch);
    }

    /**
     * 重置WiFi设置
     * 目前在退出账号的时候用
     */
    public static void resetWifiSetting() {
        putWifiWatch(true);
        putWifiDownload(true);
    }
}
