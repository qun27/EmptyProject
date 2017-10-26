package painter1024.emptyproject.biz.ui.main.boot;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import painter1024.emptyproject.R;
import painter1024.emptyproject.core.ui.base.component_ex.mvp.component.MvpActivity;
import painter1024.emptyproject.core.ui.ex.util.MsgUtil;
import painter1024.emptyproject.biz.ui.main.MainInteract;

public class MainBootActivity extends MvpActivity<MainBootPresenter>
        implements IMainBootProtocol.IView {

    private ImageView bootImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_boot);
        bootImageView = (ImageView) findViewById(R.id.bootImageView);
        initBootImage();
        findViewById(R.id.skipButton).setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.entry();
            }
        });
    }

    private void initBootImage() {
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.boot();
    }

    @NonNull
    @Override
    protected MainBootPresenter onCreatePresenter() {
        return new MainBootPresenter<>(this);
    }

    @Override
    public void jumpToHome() {
        MainInteract.jumpToHome(this, true);
    }

    @Override
    public void jumpToLogin() {
        // TODO
    }

    @Override
    public void showMsg(String msg) {
        MsgUtil.showMsg(findViewById(android.R.id.content), msg);
    }

    @Override
    public void jumpToGuide() {
        MainInteract.jumpToGuide(this);
    }

    @Override
    public void cacheImage(String url) {
        BootImageCacheUtil.diskCacheImage(url, bootImageView);
    }
}
