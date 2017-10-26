package painter1024.emptyproject.core.ui.base.component;

import android.content.Context;

import painter1024.emptyproject.core.ui.base.component.lifecycle.LifecycleFragment;


/**
 * 基础Fragment，只添加项目中所有Fragment都需要的代码
 */

public class BaseFragment extends LifecycleFragment {

    protected BaseActivity activity;

    @Override
    public void onAttach(Context context) {
        this.activity = (BaseActivity) getActivity();
        super.onAttach(context);
    }
}
