package painter1024.emptyproject.biz.ui.main.boot;

import painter1024.emptyproject.core.ui.base.component_ex.mvp.protocol.IMsgView;

/**
 * 启动页协议
 */

public interface IMainBootProtocol {

    /**
     * 启动页界面
     */
    interface IView extends IMsgView {

        void jumpToGuide();
        void jumpToHome();
        void jumpToLogin();

        /**
         * 缓存图片
         * @param url 图片地址
         */
        void cacheImage(String url);
    }

    /**
     * 启动页主持者
     */
    interface IPresenter {
        /**
         * 启动
         */
        void boot();

        /**
         * 页面跳转
         */
        void entry();
    }
}
