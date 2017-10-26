package painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import painter1024.emptyproject.app.util.LogUtil;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.Config;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.IGestureInterceptor;

/**
 * 可刷新的View代理
 */

public class RefreshAbleViewDelegate implements IRefreshAbleView {
    private static final boolean D = Config.DEBUG;
    private static final String TAG = "RefreshAbleViewDelegate";
    private final SwipeRefreshLayout view;

//    private boolean enableRefreshGesture; //直接使用了view本身的
    private boolean enableRefreshUi = true;
    private boolean enableRefresh = true;

    private boolean isRefreshing;

    private OnRefreshListener listener;

    private IGestureInterceptor interceptor;

    public RefreshAbleViewDelegate(@NonNull SwipeRefreshLayout view) {
        this.view = view;
        view.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                log("SwipeRefreshLayout onRefresh");
                if(interceptor!=null && interceptor.needIntercept()) {
                    LogUtil.e("刷新手势被拦截");
                    finishRefresh();
                    return;
                }
                RefreshAbleViewDelegate.this.onRefresh();
            }
        });
    }

    /**
     * 可用、ui可用、手势才能可用
     *
     * @param enable 是否可以手势刷新
     */
    @Override
    public void enableRefreshGesture(boolean enable) {
        log("enableRefreshGesture =" + enable);
        view.setEnabled(enable);

//        if(enable == gestureRefreshEnable) return;
//        if(!enable){
//            gestureRefreshEnable = false;
//            view.setEnabled(false);
//        } else if(enableRefresh && enableRefreshUi && enable){
//            gestureRefreshEnable = true;
//            view.setEnabled(true);
//        }
    }

    @Override
    public void enableRefreshUi(boolean enable) {
        log("enableRefreshUi =" + enable );
        enableRefreshUi = enable;
        //不能有UI，也就不能有手势
        if(!enable) enableRefreshGesture(false);
    }

    @Override
    public void enableRefresh(boolean enable) {
        log("enableRefresh = " + enable);
        enableRefresh = enable;
        //不能有刷新，也就不能有UI
        if(!enable) enableRefreshUi(false);
    }

    @Override
    public void refreshWithUi() {
        log("refreshWithUi");
        if(isRefreshing()) return;
        if(!enableRefresh) {
            LogUtil.e("enableRefresh = false, when request refreshWithUi()");
            return;
        }
        if(enableRefreshUi) {
            setRefreshingUi(true);
        } else {
            LogUtil.e("enableRefreshUi = false, when request refreshWithUi()");
        }
        onRefresh();
    }

    @Override
    public boolean isRefreshing() {
        boolean ret = isRefreshing || view.isRefreshing();
        log("isRefreshing =" + ret);
        return ret;
    }

    @Override
    public void refresh() {
        log("refresh");
        if(isRefreshing()) return;
        if(!enableRefresh) {
            LogUtil.e("enableRefresh = false, when request refresh()");
            return;
        }
        onRefresh();
    }

    @Override
    public void onRefresh() {
        log("onRefresh");
        isRefreshing = true;
        if(listener!=null) listener.onRefresh();
    }

    @Override
    public void finishRefresh() {
        log("finishRefresh");
        setRefreshingUi(false);
        isRefreshing = false;
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener listener) {
        log("setOnRefreshListener");
        this.listener = listener;
    }

    @Override
    public void setRefreshGestureInterceptor(IGestureInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public OnRefreshListener getOnRefreshListener() {
        return listener;
    }

    private void setRefreshingUi(boolean refreshing) {
        view.setRefreshing(refreshing);
    }

    private void log(String msg) {
        if(D) Log.i(TAG, msg);
    }
}
