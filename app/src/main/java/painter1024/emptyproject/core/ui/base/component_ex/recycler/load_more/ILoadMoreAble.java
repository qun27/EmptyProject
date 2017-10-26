package painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more;

/**
 * 可加载更多接口
 */
public interface ILoadMoreAble extends OnLoadMoreListener {
    boolean isLoadingMore();

    void enableLoadMore(boolean enable);

    void loadMore();

    void finishLoadMore();

    void setOnLoadMoreListener(OnLoadMoreListener listener);
}
