package painter1024.emptyproject.core.ui.base.component_ex.recycler.paging;

import android.util.Log;

import painter1024.emptyptoject.lib.util.biz.PagingHelper;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.Config;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more.ILoadMoreAbleView;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more.OnLoadMoreListener;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.loadable.LoadAbleViewDelegate;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.IRefreshAbleView;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.OnRefreshListener;

/**
 * 分页加载界面代理, TODO 生命周期
 */

public class PagingLoadViewDelegate extends LoadAbleViewDelegate implements IPagingLoadView {
    private static final boolean D = Config.DEBUG;
    private static final String TAG = "PagingLoadViewDelegate";

    private final PagingHelper pagingHelper = new PagingHelper();
    private OnPageLoadListener onPageLoadListener;

    public PagingLoadViewDelegate(IRefreshAbleView refreshAbleView, ILoadMoreAbleView loadMoreAbleView) {
        super(refreshAbleView, loadMoreAbleView);
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                onPageLoad(pagingHelper.getStartPage());
            }
        });
        setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                onPageLoad(pagingHelper.getNextPage());
            }
        });
        enableLoadMoreGesture(false);//起始时，关闭加载更多手势
    }

    @Override
    public void onPageLoad(int page) {
        log("onPageLoad =" + page);
        if(onPageLoadListener!=null) onPageLoadListener.onPageLoad(page);
    }

    @Override
    public void finishPageLoad(int page, boolean pageValid, boolean hasNextPage) {
        log("finishPageLoad page=" + page + " pageValid=" + pageValid + " hasNextPage=" + hasNextPage);
        if(pageValid) pagingHelper.flip(page, hasNextPage);//加载的页面有效，则翻页
        if( (pagingHelper.isStartPage(page) && !pageValid ) || !hasNextPage) {
            //开始页，且无效, 或者没有下一页，不能手势加载更多
            enableLoadMoreGesture(false);
        } else {
            enableLoadMoreGesture(true);
        }
        finishLoad();
    }

    @Override
    public void setOnPageLoadListener(OnPageLoadListener onPageLoadListener) {
        log("setOnPageLoadListener");
        this.onPageLoadListener = onPageLoadListener;
    }

    public OnPageLoadListener getOnPageLoadListener() {
        return onPageLoadListener;
    }

    /**
     * 是否是开始页
     * @param page 需要判断的页面编号
     */
    public boolean isStartPage(int page){
        return pagingHelper.isStartPage(page);
    }

    @Override
    public int getStartPage() {
        return pagingHelper.getStartPage();
    }

    private void log(String msg) {
        if(D) Log.i(TAG, msg);
    }
}
