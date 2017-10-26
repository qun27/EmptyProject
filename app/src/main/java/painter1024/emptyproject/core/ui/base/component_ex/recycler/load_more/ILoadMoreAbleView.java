package painter1024.emptyproject.core.ui.base.component_ex.recycler.load_more;


import painter1024.emptyproject.core.ui.base.component_ex.recycler.IGestureInterceptor;

/**
 * 可以加载更多的页面
 */

public interface ILoadMoreAbleView extends ILoadMoreAble {
    /**
     * 设置手势加载更多使能
     * @param enable 是否可以手势加载更多
     */
    void enableLoadMoreGesture(boolean enable);

    /**
     * 加载更多的UI效果使能，关闭时手势也会关闭
     * @param enable 是否有加载更多的UI效果
     */
    void enableLoadMoreUi(boolean enable);

    /**
     * 加载更多带UI效果
     */
    void loadMoreWithUi();

    /**
     * 设置手势拦截器，可拦截手势的触发
     */
    void setLoadMoreGestureInterceptor(IGestureInterceptor handler);
}
