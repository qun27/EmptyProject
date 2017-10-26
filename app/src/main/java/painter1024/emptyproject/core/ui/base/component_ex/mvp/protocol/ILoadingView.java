package painter1024.emptyproject.core.ui.base.component_ex.mvp.protocol;

/**
 * 加载中的界面接口
 */

public interface ILoadingView {
    /**
     * 显示加载
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 隐藏加载
     */
    void hideLoading();
}
