package painter1024.emptyproject.project.data.net.tool;


import android.support.annotation.NonNull;

import io.reactivex.functions.Function;
import painter1024.emptyproject.project.data.net.error.ErrorCodeException;
import painter1024.emptyproject.project.data.net.NetBean;

/**
 * NetBean拆包Function
 */

public abstract class NetBeanUnpackFunction<T> implements Function<NetBean<T>, T> {

    @Override
    public T apply(NetBean<T> input) throws Exception {
        if (!input.isOk()) {
            throw new ErrorCodeException(input.getInfo(), input.getStatus(), input.getData());
        }
        T ret = input.getData();
        if (ret == null) return returnWhenDataNull();
        return ret;
    }

    /**
     * 当拆包后的数据为空时，返回的数据实例
     *
     * @return 数据为空时返回的数据实例
     */
    @NonNull
    protected abstract T returnWhenDataNull();
}
