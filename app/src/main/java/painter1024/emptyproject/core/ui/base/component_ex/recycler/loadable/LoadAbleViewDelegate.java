package painter1024.emptyproject.core.ui.base.component_ex.recycler.loadable;

import android.util.Log;

import painter1024.emptyproject.app.util.LogUtil;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.Config;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.IGestureInterceptor;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more.ILoadMoreAbleView;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more.OnLoadMoreListener;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.IRefreshAbleView;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.OnRefreshListener;

/**
 * 加载界面代理，兼有刷新和加载更多
 * TODO 需要对外开放更多方法，考虑直接实现刷新和加载更多View接口
 */

public class LoadAbleViewDelegate implements ILoadAbleView{
    private static final boolean D = Config.DEBUG;
    private static final String TAG = "LoadAbleViewDelegate";

    private final IRefreshAbleView refreshAbleView;
    private final ILoadMoreAbleView loadMoreAbleView;

    public LoadAbleViewDelegate(final IRefreshAbleView refreshAbleView, final ILoadMoreAbleView loadMoreAbleView) {
        this.refreshAbleView = refreshAbleView;
        this.loadMoreAbleView = loadMoreAbleView;
        loadMoreAbleView.setLoadMoreGestureInterceptor(new IGestureInterceptor() {
            @Override
            public boolean needIntercept() {
                return refreshAbleView.isRefreshing();
            }
        });

        refreshAbleView.setRefreshGestureInterceptor(new IGestureInterceptor() {
            @Override
            public boolean needIntercept() {
                return loadMoreAbleView.isLoadingMore();
            }
        });
    }

    //=====================================
                    /*刷新*/
    //=====================================

    @Override
    public void refreshWithUi() {
        if(isLoading()) {
            LogUtil.d("正在加载，无法刷新");
            return;
        }
        refreshAbleView.refreshWithUi();
    }

    @Override
    public void setRefreshGestureInterceptor(IGestureInterceptor handler) {
        //不支持
    }

    @Override
    public void refresh() {
        if(isLoading()) {
            LogUtil.d("正在加载，无法刷新");
            return;
        }
        refreshAbleView.refresh();
    }

    @Override
    public void finishRefresh() {
        //no support use #finishLoad()
    }

    @Override
    public void onRefresh() {
        //no support
    }

    @Override
    public void enableRefreshGesture(boolean enable) {
        refreshAbleView.enableRefreshGesture(enable);
    }

    @Override
    public void enableRefreshUi(boolean enable) {
        refreshAbleView.enableRefreshUi(enable);
    }

    @Override
    public void enableRefresh(boolean enable) {
        refreshAbleView.enableRefresh(enable);
    }



    @Override
    public boolean isRefreshing() {
        return refreshAbleView.isRefreshing();
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener listener) {
        refreshAbleView.setOnRefreshListener(listener);
    }

    //=====================================
                    /*加载更多*/
    //=====================================
    @Override
    public void loadMoreWithUi() {
        if(isLoading()) {
            LogUtil.d("正在加载，记载更多");
            return;
        }
        loadMoreAbleView.loadMoreWithUi();
    }

    @Override
    public void setLoadMoreGestureInterceptor(IGestureInterceptor handler) {
        //不支持
    }

    @Override
    public void loadMore() {
        if(isLoading()) {
            LogUtil.d("正在加载，记载更多");
            return;
        }
        loadMoreAbleView.loadMore();
    }

    @Override
    public void finishLoadMore() {
        //no support use #finishLoad()
    }

    @Override
    public void onLoadMore() {
        //no support
    }

    @Override
    public void enableLoadMoreGesture(boolean enable) {
        loadMoreAbleView.enableLoadMoreGesture(enable);
    }

    @Override
    public void enableLoadMoreUi(boolean enable) {
        loadMoreAbleView.enableLoadMoreUi(enable);
    }

    @Override
    public void enableLoadMore(boolean enable) {
        loadMoreAbleView.enableLoadMore(enable);
    }

    @Override
    public boolean isLoadingMore() {
        return loadMoreAbleView.isLoadingMore();
    }


    @Override
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        loadMoreAbleView.setOnLoadMoreListener(listener);
    }

    //=====================================
                    /*可加载view*/
    //=====================================
    @Override
    public void finishLoad() {
        log("finishLoad");
        if(refreshAbleView.isRefreshing()) refreshAbleView.finishRefresh();
        if(loadMoreAbleView.isLoadingMore()) loadMoreAbleView.finishLoadMore();
    }

    @Override
    public boolean isLoading() {
        boolean ret = (refreshAbleView.isRefreshing() || loadMoreAbleView.isLoadingMore());
        log("isLoading =" + ret);
        return ret;
    }

    @Override
    public void enableLoadGesture(boolean enable) {
        log("enableLoadGesture =" + enable);
        refreshAbleView.enableRefreshGesture(enable);
        loadMoreAbleView.enableLoadMoreGesture(enable);
    }

    @Override
    public void enableLoadUi(boolean enable) {
        log("enableLoadGesture =" + enable);
        refreshAbleView.enableRefreshUi(enable);
        loadMoreAbleView.enableLoadMoreUi(enable);
    }

    private void log(String msg) {
        if(D) Log.i(TAG, msg);
    }
}
