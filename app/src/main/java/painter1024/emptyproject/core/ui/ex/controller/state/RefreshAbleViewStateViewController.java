package painter1024.emptyproject.core.ui.ex.controller.state;

import android.view.View;

import painter1024.emptyproject.core.ui.base.component_ex.recycler.refresh.IRefreshAbleView;

/**
 * 可刷新view的状态view控制器
 */

public class RefreshAbleViewStateViewController extends StateViewController{

    private final IRefreshAbleView refreshAbleView;

    public RefreshAbleViewStateViewController(View rootView, IRefreshAbleView refreshAbleView) {
        super(rootView);
        this.refreshAbleView = refreshAbleView;
        setOnErrorViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RefreshAbleViewStateViewController.this.refreshAbleView.refreshWithUi();
                setState(StateViewController.State.NORMAL);
            }
        });
    }
}
