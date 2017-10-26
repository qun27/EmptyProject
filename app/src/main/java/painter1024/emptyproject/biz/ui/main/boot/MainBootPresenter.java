package painter1024.emptyproject.biz.ui.main.boot;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.functions.Consumer;
import painter1024.emptyproject.BuildConfig;
import painter1024.emptyproject.core.ui.base.component.lifecycle.ILifecycleComponent;
import painter1024.emptyproject.core.ui.base.component_ex.mvp.presenter.AbsActivityPresenter;
import painter1024.emptyproject.biz.data.boot.logic.GetBootImageLogic;
import painter1024.emptyproject.biz.data.boot.store.sp.VersionCodeSP;

/**
 * 启动页主持者
 */

public class MainBootPresenter<V extends ILifecycleComponent<ActivityEvent> & IMainBootProtocol.IView>
        extends AbsActivityPresenter<V>
        implements IMainBootProtocol.IPresenter {

    private static final long COUNTDOWNINTERVAL = 1000;
    private static final long MILLISINFUTURE = 3000;
    private CountDownTimer timer = new CountDownTimer(MILLISINFUTURE, COUNTDOWNINTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
        }
        @Override
        public void onFinish() {
            entry();
        }
    };

    public MainBootPresenter(@NonNull V view) {
        super(view);
    }

    @Override
    public void boot() {
        //启动，延时和跳转逻辑处理
        timer.start();
        new GetBootImageLogic().getImageUrl()
                .compose(provider.<String>bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String url) throws Exception {
                        view.cacheImage(url);
                    }
                });
    }

    @Override
    public void entry() {
        timer.cancel();
        int localVersionCode = VersionCodeSP.getVersionCode();
        if (BuildConfig.VERSION_CODE > localVersionCode) {
            VersionCodeSP.putVersionCode(BuildConfig.VERSION_CODE);
            view.jumpToGuide();
            return;
        }
        view.jumpToHome();
    }
}
