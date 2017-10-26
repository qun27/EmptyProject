package painter1024.emptyproject.core.ui.base.component_ex.recycler.paging;

import painter1024.emptyproject.core.ui.base.component_ex.recycler.loadable.ILoadAbleView;

/**
 * 分页加载界面
 */

public interface IPagingLoadView extends OnPageLoadListener, ILoadAbleView {
    void finishPageLoad(int page, boolean pageValid, boolean hasNextPage);

    void setOnPageLoadListener(OnPageLoadListener listener);

    boolean isStartPage(int page);

    int getStartPage();
}
