package painter1024.emptyproject.app.util;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.LogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;

import painter1024.emptyproject.BuildConfig;

/**
 * Log打印工具
 */
public class LogUtil {

    public static boolean DEBUG =  BuildConfig.DEBUG;

    static {
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    public static boolean isDebug() {
        return DEBUG;
    }

    public static void setDebug(boolean debug) {
        DEBUG = debug;
    }

    public static void printer(Printer printer) {
        if(DEBUG) Logger.printer(printer);
    }

    public static void addLogAdapter(LogAdapter adapter) {
        if(DEBUG) Logger.addLogAdapter(adapter);
    }

    public static void clearLogAdapters() {
        if(DEBUG) Logger.clearLogAdapters();
    }

    public static Printer t(String tag) {
        return Logger.t(tag);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if(DEBUG) Logger.log(priority, tag, message, throwable);
    }

    public static void d(String message, Object... args) {
        if(DEBUG) Logger.d(message, args);
    }

    public static void d(Object object) {
        if(DEBUG) Logger.d(object);
    }

    public static void e(String message, Object... args) {
        if(DEBUG) Logger.e(message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if(DEBUG) Logger.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        if(DEBUG) Logger.i(message, args);
    }

    public static void v(String message, Object... args) {
        if(DEBUG) Logger.v(message, args);
    }

    public static void w(String message, Object... args) {
        if(DEBUG) Logger.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        if(DEBUG) Logger.wtf(message, args);
    }

    public static void json(String json) {
        if(DEBUG) Logger.json(json);
    }

    public static void xml(String xml) {
        if(DEBUG) Logger.xml(xml);
    }
}
