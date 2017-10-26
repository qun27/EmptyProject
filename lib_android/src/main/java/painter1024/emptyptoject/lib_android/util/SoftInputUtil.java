package painter1024.emptyptoject.lib_android.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * 软键盘工具类
 */
public class SoftInputUtil {

    /**
     * 关闭软键盘
     * @param view 需要关闭软键盘的view
     */
    public static void closeSoftInput(Context context, View view) {
        if (context == null || view ==null) return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    /**
     * 关闭软键盘
     */
    public static void closeSoftInput(Activity activity) {
        if (activity == null || activity.getCurrentFocus() == null) return;
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 打开软键盘
     * @param view 需要打开软键盘的view
     */
    public static void openSoftInput(Activity activity, View view) {
        if (activity == null) return;
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }
}
