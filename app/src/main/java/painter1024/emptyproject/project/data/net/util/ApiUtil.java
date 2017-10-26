package painter1024.emptyproject.project.data.net.util;

import android.content.Intent;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import painter1024.emptyproject.app.MyApplication;
import painter1024.emptyproject.app.util.LogUtil;
import painter1024.emptyproject.project.data.net.error.ErrorCodeException;
import painter1024.emptyproject.project.data.net.NetBean;
import painter1024.emptyproject.project.data.net.RetrofitClient;

/**
 * API 工具
 */

public class ApiUtil {
    public static final String ACTION_API_REQUEST_ERROR = "ACTION_API_REQUEST_ERROR";
    public static final String KEY_API_REQUEST_ERROR = "KEY_API_REQUEST_ERROR";

    public static <E extends NetBean, API>Observable<E> request(Class<API> cls, Invoker<E, API> invoker) {
        API api = RetrofitClient.retrofit().create(cls);
        Observable<E> observable = invoker.invoke(api);
        //do something before return
        return observable
                .map(new Function<E, E>() {
                    @Override
                    public E apply(E netBean) throws Exception {
                        //Api 返回的异常，转移到异常通道
                        if (!netBean.isOk()) {
                            throw new ErrorCodeException(netBean.getInfo(),
                                    netBean.getStatus(), netBean.getData());
                        }
                        return netBean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if(LogUtil.isDebug()) throwable.printStackTrace();
                        Intent intent = new Intent(ACTION_API_REQUEST_ERROR);
                        intent.putExtra(KEY_API_REQUEST_ERROR, new ApiRequestException(throwable));
                        MyApplication.getInstance().sendBroadcast(intent);
                    }
                });
    }

    public interface Invoker<E, API> {
        Observable<E> invoke(API api);
    }

    public static class ApiRequestException extends Exception {

        public ApiRequestException(Throwable cause) {
            super(cause);
        }
    }
}
