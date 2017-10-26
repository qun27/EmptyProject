package painter1024.emptyproject.core.ui.base.component_ex.mvp.presenter;

import android.support.annotation.NonNull;

import com.trello.navi2.Event;
import com.trello.navi2.Listener;
import com.trello.navi2.NaviComponent;

/**
 * 抽象的 Navi Presenter类 <br/>
 */
public abstract class AbsNaviPresenter<V extends NaviComponent>
        extends AbsPresenter<V>
        implements NaviComponent {

    protected NaviComponent naviComponent;

    public AbsNaviPresenter(@NonNull V view) {
        super(view);
        naviComponent = view;
    }

    @Override
    public final boolean handlesEvents(Event... events) {
        return naviComponent.handlesEvents(events);
    }

    @Override
    public final <T> void addListener(@NonNull Event<T> event, @NonNull Listener<T> listener) {
        naviComponent.addListener(event, listener);
    }

    @Override
    public final <T> void removeListener(@NonNull Listener<T> listener) {
        naviComponent.removeListener(listener);
    }
}
