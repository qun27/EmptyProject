package painter1024.emptyproject.core.ui.ex.controller.navigation;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;

import painter1024.emptyproject.R;

/**
 * 导航控制器
 */

public class NavigationController {

    private View backView, homeView;

    public NavigationController(Activity activity) {
        backView = activity.findViewById(R.id.backView);
        homeView = activity.findViewById(R.id.homeView);
        if (backView != null) backView.setOnClickListener(new NavigationBackDefListener(activity));
        if (homeView != null) homeView.setOnClickListener(new NavigationHomeDefListener(activity));
    }

    public void setOnBackViewClickListener(View.OnClickListener listener) {
        if (backView != null) {
            backView.setOnClickListener(listener);
        }
    }

    public void setOnHomeViewClickListener(View.OnClickListener listener) {
        if (homeView != null) {
            homeView.setOnClickListener(listener);
        }
    }

    @Nullable
    protected View getBackView() {
        return backView;
    }

    @Nullable
    protected View getHomeView() {
        return homeView;
    }
}
