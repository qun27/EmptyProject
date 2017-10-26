package painter1024.emptyptoject.lib_android.ui.uitl.anim;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;

import painter1024.emptyptoject.lib_android.util.SDKVersionUtil;


/**
 * 转场动画工具
 */
public class TransitionAnimUtil {

    /**
     * 从一个view开启一个activity，从该view绽放的动画
     * 只在SDK16以上有动画效果，低版本是一般动画
     * @param activity 启动的activity
     * @param intent 需要启动的意图
     * @param requestCode 请求码
     * @param view 启动的view
     */
    public static void startActivityForResultScaleFromView(Activity activity, Intent intent, int requestCode, View view){
        //在高版本可以有更好的动画效果
        if (SDKVersionUtil.hasJellyBean()) {
            // makeThumbnailScaleUpAnimation() looks kind of ugly here as the loading spinner may
            // show plus the thumbnail image in GridView is cropped. so using
            // makeScaleUpAnimation() instead.
            ActivityOptions options =
                    ActivityOptions.makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight());
            activity.startActivityForResult(intent, requestCode, options.toBundle());
        } else {
            activity.startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 从一个view开启一个activity，从该view绽放的动画
     * 只在SDK16以上有动画效果，低版本是一般动画
     * @param activity 启动的activity
     * @param intent 需要启动的意图
     * @param view 启动的view
     */
    public static void startActivityScaleFromView(Activity activity, Intent intent, View view){
        startActivityForResultScaleFromView(activity, intent, -1, view);
    }
}
