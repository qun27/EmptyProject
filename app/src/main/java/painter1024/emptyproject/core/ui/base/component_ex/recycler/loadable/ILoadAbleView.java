package painter1024.emptyproject.core.ui.base.component_ex.recycler.loadable;

import painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more.ILoadMoreAbleView;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.IRefreshAbleView;

/**
 * 可加载页面接口，包含刷新和加载更多
 */

public interface ILoadAbleView extends IRefreshAbleView, ILoadMoreAbleView {
    void finishLoad();
    boolean isLoading();

    /**
     * 设置手势加载使能，包括刷新手势和加载更多手势
     * @param enable 是否可以手势加载
     */
    void enableLoadGesture(boolean enable);
    /**
     * 设置UI使能，包括刷新手势和加载的UI
     * @param enable 是否有UI效果
     */
    void enableLoadUi(boolean enable);
}
