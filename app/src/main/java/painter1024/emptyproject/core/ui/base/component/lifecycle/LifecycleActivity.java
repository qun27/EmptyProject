package painter1024.emptyproject.core.ui.base.component.lifecycle;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.navi2.component.support.NaviAppCompatActivity;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import painter1024.emptyproject.app.util.LogUtil;

/**
 * 生命周期Activity，提供对外的生命周期监听器
 */

public class LifecycleActivity extends NaviAppCompatActivity implements ILifecycleComponent<ActivityEvent>{
    // TODO: 2017/1/9 实现Activity生命周期的对外回调
    private static boolean D = false;

    protected final LifecycleProvider<ActivityEvent> provider
            = NaviLifecycle.createActivityLifecycleProvider(this);

    public LifecycleProvider<ActivityEvent> getLifecycleProvider() {
        return provider;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(D) LogUtil.d("onCreate");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(D) LogUtil.d("onPostCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(D) LogUtil.d("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(D) LogUtil.d("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(D) LogUtil.d("onResume");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(D) LogUtil.d("onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(D) LogUtil.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(D) LogUtil.d("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(D) LogUtil.d("onDestroy");
    }
}
