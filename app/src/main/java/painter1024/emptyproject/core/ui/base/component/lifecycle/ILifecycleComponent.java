package painter1024.emptyproject.core.ui.base.component.lifecycle;

import com.trello.navi2.NaviComponent;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * 生命周期组件
 */

public interface ILifecycleComponent<E> extends NaviComponent{
    LifecycleProvider<E> getLifecycleProvider();
}
