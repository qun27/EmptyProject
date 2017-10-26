package painter1024.emptyproject.core.ui.ex.util;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import painter1024.emptyproject.app.MyApplication;

/**
 * 消息工具
 */

public class MsgUtil {

    public static final int LENGTH_SHORT = 0;
    public static final int LENGTH_LONG = 1;

    /**
     * 显示消息，让处于最上层的页面进行展示
     * @param msg The text to show.
     */
    public static void showMsg(String msg){
        showToast(msg);
    }

    /**
     * 显示消息
     * @param view The view to find a parent from.
     * @param msg The text to show.  Can be formatted text.
     */
    public static void showMsg(View view, CharSequence msg){
//        new Builder().make(view, msg, LENGTH_SHORT).show();
        showToast(msg);
    }

    /**
     * 显示消息
     * @param view The view to find a parent from.
     * @param msg The text to show.  Can be formatted text.
     * @param action Text to display for the action
     * @param listener callback to be invoked when the action is clicked
     * @deprecated
     */
    public static void showMsg(View view, CharSequence msg, CharSequence action, View.OnClickListener listener){
//        new Builder().make(view, msg, LENGTH_SHORT).setAction(action, listener).show();
        showToast(msg);
    }

    /**
     * 显示消息,Toast方式。
     * 优先使用 {@link #showMsg(View, CharSequence)}
     * @param msg The text to show.  Can be formatted text.
     */
    public static void showToast(CharSequence msg){
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    public static class Builder {

        View view;
        CharSequence msg;
        int duration;
        CharSequence action;
        View.OnClickListener listener;

        /**
         * 创建
         * @param view     The view to find a parent from.
         * @param msg     The text to show.  Can be formatted text.
         * @param duration How long to display the message.  Either {@link #LENGTH_SHORT} or {@link
         *                 #LENGTH_LONG}
         */
        public Builder make(View view, CharSequence msg, int duration){
            this.view = view;
            this.msg = msg;
            this.duration = duration;
            return this;
        }

        /**
         * 设置按钮事件
         * @param action     Text to display for the action
         * @param listener callback to be invoked when the action is clicked
         */
        public Builder setAction(CharSequence action, View.OnClickListener listener){
            this.action = action;
            this.listener = listener;
            return this;
        }

        /**
         * 显示
         */
        public void show(){
            int tmp = convertDuration(duration);
            Snackbar.make(view, msg, tmp).setAction(action, listener).show();
        }

        private int convertDuration(int duration) {
            switch (duration){
                case LENGTH_SHORT:
                    return Snackbar.LENGTH_SHORT;
                case LENGTH_LONG:
                    return Snackbar.LENGTH_LONG;
                default:
                    return Snackbar.LENGTH_SHORT;
            }
        }
    }
}
