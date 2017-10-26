package painter1024.emptyproject.core.ui.base.component_ex.mvp.presenter;

import android.support.annotation.NonNull;

/**
 * 抽象的Presenter类 <br/>
 */
public abstract class AbsPresenter<V> {

    protected final V view;

    public AbsPresenter(@NonNull V view) {
        this.view = view;
    }
}
