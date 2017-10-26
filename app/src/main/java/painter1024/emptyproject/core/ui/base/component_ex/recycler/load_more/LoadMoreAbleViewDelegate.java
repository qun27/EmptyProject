package painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import painter1024.emptyproject.app.util.LogUtil;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.Config;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.IGestureInterceptor;

/**
 * TODO 加载更多页面代理
 */

public class LoadMoreAbleViewDelegate implements ILoadMoreAbleView {
    private static final boolean D = Config.DEBUG;
    private static final String TAG = "LoadMoreViewDelegate";

    private final RecyclerView recyclerView;

    private boolean enableLoadMoreGesture = true;
    private boolean enableLoadMoreUi = true;
    private boolean enableLoadMore = true;

    private boolean isLoadingMore;

    private OnLoadMoreListener listener;

    private IGestureInterceptor interceptor;

    public LoadMoreAbleViewDelegate(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        recyclerView.addOnScrollListener(loadMoreOnScrollListener);
    }

    @Override
    public void enableLoadMoreGesture(boolean enable) {
        log("enableLoadMoreGesture =" + enable);
        enableLoadMoreGesture = enable;
    }

    @Override
    public void enableLoadMoreUi(boolean enable) {
        log("enableLoadMoreUi =" + enable);
        enableLoadMoreUi = enable;
        //不能有UI，也就不能有手势
        if(!enable) enableLoadMoreGesture(false);
    }

    @Override
    public void enableLoadMore(boolean enable) {
        log("enableLoadMore" + enable);
        enableLoadMore = enable;
        ///不能有加载更多，也就不能有UI
        if(!enable) enableLoadMoreUi(false);
    }

    @Override
    public void loadMoreWithUi() {
        log("loadMoreWithUi");
        if(isLoadingMore()) return;
        if(!enableLoadMore) {
            LogUtil.e("enableLoadMore = false, when request loadMoreWithUi()");
            return;
        }
        loadMore();
        if(enableLoadMoreUi) {
            //加载更多UI效果
        } else {
            LogUtil.e("enableRefreshUi = false, when request refreshWithUi()");
        }
    }

    @Override
    public boolean isLoadingMore() {
        boolean ret = isLoadingMore /*|| view.isLoadingMore()*/;
        log("isLoadingMore =" + ret);
        return ret;
    }

    @Override
    public void loadMore() {
        log("loadMore");
        if(isLoadingMore()) return;
        if(!enableLoadMore) {
            LogUtil.e("enableRefresh = false, when request refresh()");
            return;
        }
        onLoadMore();
    }

    @Override
    public void onLoadMore() {
        log("onLoadMore");
        isLoadingMore = true;
        if(listener!=null) listener.onLoadMore();
    }

    @Override
    public void finishLoadMore() {
        log("finishLoadMore");
        //相应UI效果变比

        isLoadingMore = false;
    }

    @Override
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        log("setOnLoadMoreListener");
        this.listener = listener;
    }

    @Override
    public void setLoadMoreGestureInterceptor(IGestureInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public OnLoadMoreListener getOnLoadMoreListener() {
        return listener;
    }

    private void log(String msg) {
        if(D) Log.i(TAG, msg);
    }

    /**
     * 滚动监听器，用于检测RecyclerView滚动时到达一定程度时，调用加载更多的方法。
     */
    final private RecyclerView.OnScrollListener loadMoreOnScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if(!enableLoadMore) return;
            if(!enableLoadMoreGesture) return;
            //回调外部，让外部有拦截手势的机会
            if(interceptor!=null && interceptor.needIntercept()) {
                LogUtil.e("加载更多手势被拦截");
                return;
            }

            //加载更多
            RecyclerView.LayoutManager lm = recyclerView.getLayoutManager();
            int lastVisibleItem = ((LinearLayoutManager) lm).findLastVisibleItemPosition();
            int totalItemCount = lm.getItemCount();

            //最后一项
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && enableLoadMore && !isLoadingMore
                    && lastVisibleItem == totalItemCount - 1) {
                isLoadingMore = true;
                log("RecyclerView onLoadMore");
                onLoadMore();
            }
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

}
