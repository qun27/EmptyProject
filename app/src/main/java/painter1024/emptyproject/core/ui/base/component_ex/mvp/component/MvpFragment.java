package painter1024.emptyproject.core.ui.base.component_ex.mvp.component;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import painter1024.emptyproject.core.ui.base.component.BaseFragment;
import painter1024.emptyproject.core.ui.base.component_ex.mvp.presenter.AbsFragmentPresenter;


/**
 * 支持Mvp
 * @see AbsFragmentPresenter
 */

public abstract class MvpFragment<P extends AbsFragmentPresenter> extends BaseFragment {

    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (presenter == null) presenter = onCreatePresenter();
    }

    @NonNull
    protected abstract P onCreatePresenter();
}
