package painter1024.emptyproject.core.ui.base.component_ex.recycler.paging;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import painter1024.emptyproject.R;
import painter1024.emptyproject.app.util.LogUtil;
import painter1024.emptyproject.app.util.ResUtil;
import painter1024.emptyproject.core.ui.base.component.BaseFragment;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.IGestureInterceptor;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more.ILoadMoreAbleView;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more.LoadMoreAbleViewDelegate;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more.OnLoadMoreListener;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.IRefreshAbleView;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.OnRefreshListener;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.RefreshAbleViewDelegate;

/**
 * 分页加载
 * @see R.id#refreshLayout
 * @see R.id#recyclerView
 */

public abstract class PagingLoadFragment extends BaseFragment
        implements IPagingLoadView {
    public static final boolean D = false;

    private IPagingLoadView pagingLoadView;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;

    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private PagingFooterAdapter pagingFooterAdapter;

    private Config config = new Config();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(pagingLoadView == null) pagingLoadView = createPagingLoadView();
        pagingLoadView.setOnPageLoadListener(this);
        setupByConfig(pagingLoadView, config);
        enableFooter(config.enableFooter);
        //刷新
        pagingLoadView.refreshWithUi();
    }

    @Override
    public void finishPageLoad(int page, boolean pageValid, boolean hasNextPage) {
        if(pagingLoadView==null) return;
        pagingLoadView.finishPageLoad(page, pageValid, hasNextPage);
        if (D) LogUtil.d("结束分页的加载 page=" + page + " pageValid="+ pageValid + " hasNextPage=" + hasNextPage);
        //根据情况更换页脚文本
        if (pagingFooterAdapter !=null) {
            pagingFooterAdapter.setLastPage(PagingUtil.isLastPage(hasNextPage, getAdapter().getItemCount()));
        }
    }

    /**
     * 结束单页加载
     * @param pageValid 页面是否有效
     */
    public void finishSinglePageLoad(boolean pageValid) {
        finishPageLoad(getStartPage(), pageValid, false);
    }

    /**
     * 页脚使能
     */
    public void enableFooter(boolean enable) {
        config.enableFooter = enable;
        if (pagingFooterAdapter !=null) {
            pagingFooterAdapter.enable(enable);
        }
    }


    @NonNull
    protected IPagingLoadView createPagingLoadView(){
        refreshLayout = prepareSwipeRefreshLayout();
        recyclerView = prepareRecyclerView();
        IRefreshAbleView refreshAbleView = new RefreshAbleViewDelegate(refreshLayout);
        ILoadMoreAbleView loadMoreAbleView = new LoadMoreAbleViewDelegate(recyclerView);
        return new PagingLoadViewDelegate(refreshAbleView, loadMoreAbleView);
    }

    /**
     * 准备SwipeRefreshLayout<br/> TODO 更好的命名？<br/>
     * 并调用 {@link #onInitRefreshLayout(SwipeRefreshLayout)} 进行初始化
     */
    @NonNull
    protected SwipeRefreshLayout prepareSwipeRefreshLayout(){
        View view = getView().findViewById(R.id.refreshLayout);
        if(view instanceof SwipeRefreshLayout) {
            SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) view;
            initRefreshLayout(refreshLayout);
            return refreshLayout;
        }
        throw new IllegalStateException("prepareSwipeRefreshLayout() method return null");
    }

    /**
     * 准备RecyclerView <br/>TODO 更好的命名？<br/>
     * 并调用 {@link #onInitRecyclerView(RecyclerView)} 进行初始化
     */
    @NonNull
    protected RecyclerView prepareRecyclerView() {
        View view = getView().findViewById(R.id.recyclerView);
        if(view instanceof RecyclerView){
            RecyclerView recyclerView = (RecyclerView) view;
            initRecyclerView(recyclerView);
            return recyclerView;
        }
        throw new IllegalStateException("prepareRecyclerView() method return null");
    }

    /**
     * 初始化SwipeRefreshLayout
     */
    protected void initRefreshLayout(SwipeRefreshLayout refreshLayout) {
        onInitRefreshLayout(refreshLayout);
    }

    /**
     * 对SwipeRefreshLayout进行初始化<br/>
     * 在默认的 {@link #prepareSwipeRefreshLayout()} ()} 中被调用
     * @param refreshLayout 要被初始化的SwipeRefreshLayout
     */
    protected void onInitRefreshLayout(SwipeRefreshLayout refreshLayout) {
        refreshLayout.setColorSchemeColors(ResUtil.getColorOfAttr(activity, R.attr.colorAccent));
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView(RecyclerView recyclerView){
        if(layoutManager == null) layoutManager =  new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(layoutManager);
        if(adapter!=null) recyclerView.setAdapter(ensureLastPageFooterAdapter(adapter));
        onInitRecyclerView(recyclerView);
    }

    /**
     * 对RecyclerView进行初始化<br/>
     * 在默认的 {@link #prepareRecyclerView()} 中被调用
     */
    protected void onInitRecyclerView(RecyclerView recyclerView) {
        //do nothing
    }

    /**
     * 获取适配器
     * @return 适配器
     */
    public RecyclerView.Adapter getAdapter() {
//        if(recyclerView != null) return recyclerView.getAdapter();
        return adapter;
    }

    /**
     * 设置适配器
     * @param adapter RecyclerView的适配器
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        if(recyclerView!=null) recyclerView.setAdapter(ensureLastPageFooterAdapter(adapter));
    }

    /**
     * 保证是带有最后一页页脚的适配器
     */
    private RecyclerView.Adapter ensureLastPageFooterAdapter(RecyclerView.Adapter adapter){
        if (adapter instanceof PagingFooterAdapter) {
            pagingFooterAdapter = (PagingFooterAdapter) adapter;
        }
        pagingFooterAdapter = new PagingFooterAdapter(adapter);
        return pagingFooterAdapter;
    }

    /**
     * 获取布局管理器
     * @return 布局管理器
     */
    public RecyclerView.LayoutManager getLayoutManager() {
        if(recyclerView != null) return recyclerView.getLayoutManager();
        return layoutManager;
    }

    /**
     * 设置布局管理器
     * @param layoutManager 布局管理器
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        if(recyclerView!=null) recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * 获取RecyclerView<br/>
     * 在{@link #onActivityCreated(Bundle)}之后才能被调用
     */
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    /**
     * SwipeRefreshLayout<br/>
     * 在{@link #onActivityCreated(Bundle)}之后才能被调用
     */
    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    /**
     * 获取维护的IPagingLoadView
     */
    public IPagingLoadView getPagingLoadView() {
        return pagingLoadView;
    }

    /**
     * 设置PagingLoadView，需要在onCreate方法中设置
     * @param pagingLoadView IPagingLoadView的实现
     * @see IPagingLoadView
     */
    public void setPagingLoadView(IPagingLoadView pagingLoadView) {
        if(this.pagingLoadView != null) {
            LogUtil.e("pagingLoadView 只能设置一次");
            return;
        }
        this.pagingLoadView = pagingLoadView;
    }

    //=====================================
                    /*相关代理方法*/
    //=====================================

    @Override
    public void setOnPageLoadListener(OnPageLoadListener listener) {
        //不支持
    }

    @Override
    public boolean isStartPage(int page) {
        return pagingLoadView.isStartPage(page);
    }

    @Override
    public int getStartPage() {
        return pagingLoadView.getStartPage();
    }

    @Override
    public void finishLoad() {
        if(pagingLoadView==null) return;
        pagingLoadView.finishLoad();
    }

    @Override
    public boolean isLoading() {
        if(pagingLoadView==null) return false;
        return pagingLoadView.isLoading();
    }

    @Override
    public void refreshWithUi() {
        if(pagingLoadView==null) return;
        pagingLoadView.refreshWithUi();
    }

    @Override
    public void enableRefreshGesture(boolean enable) {
        config.gestureRefreshEnable = enable;
        if(pagingLoadView!=null) pagingLoadView.enableRefreshGesture(enable);
    }

    @Override
    public void enableRefreshUi(boolean enable) {
        config.refreshUiEnable = enable;
        if(pagingLoadView!=null)pagingLoadView.enableRefreshUi(enable);
    }

    @Override
    public void enableRefresh(boolean enable) {
        config.enableRefresh = enable;
        if(pagingLoadView!=null) pagingLoadView.enableRefresh(enable);
    }

    @Override
    public void finishRefresh() {
        if(pagingLoadView==null) return;
        pagingLoadView.finishRefresh();
    }

    @Override
    public boolean isRefreshing() {
        if(pagingLoadView==null) return false;
        return pagingLoadView.isRefreshing();
    }

    @Override
    public void refresh() {
        if(pagingLoadView==null) return;
        pagingLoadView.refresh();
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener listener) {
        //不支持
    }

    @Override
    public void setRefreshGestureInterceptor(IGestureInterceptor handler) {
        //不支持
    }

    @Override
    public void onRefresh() {
        //不支持
    }

    @Override
    public void loadMoreWithUi() {
        if(pagingLoadView==null) return;
        pagingLoadView.loadMoreWithUi();
    }

    @Override
    public void enableLoadMoreGesture(boolean enable) {
        config.gestureLoadMoreEnable = enable;
        if(pagingLoadView!=null) pagingLoadView.enableLoadMoreGesture(enable);
    }

    @Override
    public void enableLoadMoreUi(boolean enable) {
        config.loadMoreUiEnable = enable;
        if(pagingLoadView!=null)pagingLoadView.enableLoadMoreUi(enable);
    }

    @Override
    public void enableLoadMore(boolean enable) {
        config.enableLoadMore = enable;
        if(pagingLoadView!=null) pagingLoadView.enableLoadMore(enable);
    }

    @Override
    public void finishLoadMore() {
        if(pagingLoadView==null) return;
        pagingLoadView.finishLoadMore();
    }

    @Override
    public boolean isLoadingMore() {
        if(pagingLoadView==null) return false;
        return pagingLoadView.isLoadingMore();
    }

    @Override
    public void loadMore() {
        if(pagingLoadView==null) return;
        pagingLoadView.loadMore();
    }

    @Override
    public void enableLoadGesture(boolean enable) {
        config.gestureLoadEnable = enable;
        if(pagingLoadView!=null) pagingLoadView.enableLoadGesture(enable);
    }

    @Override
    public void enableLoadUi(boolean enable) {
        config.loadUiEnable = enable;
        if(pagingLoadView!=null) pagingLoadView.enableLoadUi(enable);
    }

    @Override
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        //不支持
    }

    @Override
    public void setLoadMoreGestureInterceptor(IGestureInterceptor handler) {
        //不支持
    }

    @Override
    public void onLoadMore() {
        //不支持
    }

    /**
     * 根据config相关状态进行设置
     */
    private void setupByConfig(IPagingLoadView view, Config config) {
        view.enableRefresh(config.enableRefresh);
        view.enableRefreshGesture(config.gestureRefreshEnable);
        view.enableRefreshUi(config.refreshUiEnable);

        view.enableLoadMore(config.enableLoadMore);
        view.enableLoadMoreGesture(config.gestureLoadMoreEnable);
        view.enableLoadMoreUi(config.loadMoreUiEnable);

        if(!config.gestureLoadEnable){
            //如果设置了不可用手势进行加载才配置,
            // 与gestureRefreshEnable、gestureLoadMoreEnable有直接关系
            view.enableLoadGesture(false);
        }

        if(!config.loadUiEnable){
            //如果设置了加载无UI才配置,
            // 与uiRefreshEnable、uiLoadMoreEnable有直接关系
            view.enableLoadUi(false);
        }
    }

    private class Config {
        //刷新
        boolean enableRefresh = true;
        boolean gestureRefreshEnable = true;
        boolean refreshUiEnable = true;

        //加载更多
        boolean enableLoadMore = true;
        boolean gestureLoadMoreEnable = true;
        boolean loadMoreUiEnable = true;

        //加载
        private boolean gestureLoadEnable = true;
        private boolean loadUiEnable = true;

        //页脚
        private boolean enableFooter = false;
    }
}
