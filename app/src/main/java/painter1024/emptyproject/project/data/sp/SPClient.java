package painter1024.emptyproject.project.data.sp;

import android.content.SharedPreferences;

import java.util.Set;

import painter1024.emptyproject.app.MyApplication;

import static android.content.Context.MODE_PRIVATE;

/**
 * SharedPreferences 轻量级存储客户端
 */

public class SPClient {

    public static final String NAME = "SPClient";

    private static SharedPreferences sp;

    static {
        sp = MyApplication.getInstance().getSharedPreferences(NAME, MODE_PRIVATE);
    }

    /**
     * 得到默认的轻量级客户端
     * @return 默认的轻量级客户端
     */
    public static SharedPreferences sp(){
        return sp;
    }

    /**
     * 得到默认的轻量级客户端编辑器
     * @return 默认的轻量级客户端
     */
    public static SharedPreferences.Editor editor(){
        return sp.edit();
    }

    public static boolean putInt(String key, int value) {
        return editor().putInt(key, value).commit();
    }

    public static boolean putString(String key, String value) {
        return editor().putString(key, value).commit();
    }

    public static boolean putBoolean(String key, boolean value) {
        return editor().putBoolean(key, value).commit();
    }

    public static boolean putStringSet(String key, Set<String> values) {
        return editor().putStringSet(key, values).commit();
    }

    public static boolean putLong(String key, long value) {
        return editor().putLong(key, value).commit();
    }

    public static boolean putFloat(String key, float value) {
        return editor().putFloat(key, value).commit();
    }

    public static boolean remove(String key) {
        return editor().remove(key).commit();
    }

    public static boolean clear() {
        return editor().clear().commit();
    }
}
