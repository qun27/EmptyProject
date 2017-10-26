package painter1024.emptyproject.core.ui.base.component_ex.mvp.component;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import painter1024.emptyproject.core.ui.base.component_ex.mvp.presenter.AbsActivityPresenter;
import painter1024.emptyproject.core.ui.base.component_ex.recycler.paging.PagingLoadActivity;


/**
 * 支持Mvp的activity
 * @see AbsActivityPresenter
 */

public abstract class MvpPagingActivity<P extends AbsActivityPresenter> extends PagingLoadActivity {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) presenter = onCreatePresenter();
    }

    @NonNull
    protected abstract P onCreatePresenter();
}
