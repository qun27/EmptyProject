package painter1024.emptyproject.core.ui.base.component_ex.mvp.presenter;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import painter1024.emptyproject.core.ui.base.component.lifecycle.ILifecycleComponent;

/**
 * 抽象的Presenter类 <br/>
 * 与 {@link painter1024.emptyproject.core.ui.base.component_ex.mvp.component.MvpActivity} 的子类配合使用
 */
public abstract class AbsActivityPresenter<V extends ILifecycleComponent<ActivityEvent>>
        extends AbsNaviPresenter<V>
        implements ILifecycleComponent<ActivityEvent> {

    protected LifecycleProvider<ActivityEvent> provider;

    public AbsActivityPresenter(@NonNull V view) {
        super(view);
        provider =  view.getLifecycleProvider();
    }

    @Override
    public LifecycleProvider<ActivityEvent> getLifecycleProvider() {
        return provider;
    }
}
