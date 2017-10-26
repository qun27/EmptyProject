package painter1024.emptyproject.core.ui.base.component_ex.mvp.presenter;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.FragmentEvent;

import painter1024.emptyproject.core.ui.base.component.lifecycle.ILifecycleComponent;

/**
 * 抽象的Presenter类 <br/>
 * 与 {@link painter1024.emptyproject.core.ui.base.component_ex.mvp.component.MvpFragment} 的子类配合使用
 */
public abstract class AbsFragmentPresenter<V extends ILifecycleComponent<FragmentEvent>>
        extends AbsNaviPresenter<V>
        implements ILifecycleComponent<FragmentEvent> {

    protected LifecycleProvider<FragmentEvent> provider;

    public AbsFragmentPresenter(@NonNull V view) {
        super(view);
        provider =  view.getLifecycleProvider();
    }

    @Override
    public LifecycleProvider<FragmentEvent> getLifecycleProvider() {
        return provider;
    }
}
