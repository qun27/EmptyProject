package painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh;

/**
 * 可刷新接口
 */
public interface IRefreshAble extends OnRefreshListener {

    boolean isRefreshing();

    void enableRefresh(boolean enable);

    void refresh();

    void finishRefresh();

    void setOnRefreshListener(OnRefreshListener listener);
}
