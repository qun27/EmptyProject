package painter1024.emptyproject.app;

import android.app.Application;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import painter1024.emptyproject.project.ui.ex.tool.error_handle.ErrorHandler;

/**
 *
 */

public class MyApplication extends Application {
    private static MyApplication INSTANCE;

    @Override
    public void onCreate() {
        INSTANCE = this;
        super.onCreate();
        initErrorHandler();
    }

    public static MyApplication getInstance(){
        return INSTANCE;
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

    void initErrorHandler() {
        if (RxJavaPlugins.getErrorHandler() == null) {
            RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    ErrorHandler.handle(throwable);
                }
            });
        }

    }
}
