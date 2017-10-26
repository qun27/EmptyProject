package painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh;

import painter1024.emptyproject.core.ui.base.component_ex.recycler.IGestureInterceptor;

/**
 * 可刷新的页面
 */

public interface IRefreshAbleView extends IRefreshAble {

    /**
     * 设置手势刷新使能
     * @param enable 是否可以手势刷新
     */
    void enableRefreshGesture(boolean enable);

    /**
     * 刷新的UI效果使能，关闭时，手势也会关闭
     * @param enable 是否有刷新的UI效果
     */
    void enableRefreshUi(boolean enable);

    /**
     * 刷新带UI效果
     */
    void refreshWithUi();

    /**
     * 设置手势拦截器，可拦截手势的触发
     */
    void setRefreshGestureInterceptor(IGestureInterceptor handler);
}
