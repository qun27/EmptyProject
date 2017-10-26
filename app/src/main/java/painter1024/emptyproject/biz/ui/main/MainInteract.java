package painter1024.emptyproject.biz.ui.main;

import android.content.Context;
import android.content.Intent;

import painter1024.emptyproject.biz.ui.main.guide.MainGuideActivity;
import painter1024.emptyproject.biz.ui.main.home.MainHomeActivity;

/**
 * main模块交互
 */

public class MainInteract {

    /**
     * 跳转到Home页<br/>
     * 如果已经Home，则不进行任何操作
     */
    public static void jumpToHome(Context context, boolean cleanTask){
        if(context instanceof MainHomeActivity) {
            return;
        }
        Intent intent = MainHomeActivity.prepareIntent(context);
        if(cleanTask){
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 跳转到引导页<br/>
     * 如果已经引导页，则不进行任何操作
     */
    public static void jumpToGuide(Context context){
        if(context instanceof MainGuideActivity) {
            return;
        }
        Intent intent = MainGuideActivity.prepareIntent(context);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
