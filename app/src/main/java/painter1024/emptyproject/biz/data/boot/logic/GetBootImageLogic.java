package painter1024.emptyproject.biz.data.boot.logic;

import android.support.annotation.NonNull;

import painter1024.emptyptoject.lib.util.base.StringUtil;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import painter1024.emptyproject.project.data.net.NetBean;
import painter1024.emptyproject.project.data.net.tool.NetBeanUnpackFunction;
import painter1024.emptyproject.project.data.net.util.ApiUtil;
import painter1024.emptyproject.biz.data.boot.store.api.GetBootImageApi;
import painter1024.emptyproject.biz.data.boot.store.model.BootImageBean;

/**
 * 获取启动页图片
 */

public class GetBootImageLogic {
    public Observable<String> getImageUrl() {
        return ApiUtil.request(GetBootImageApi.class,
                new ApiUtil.Invoker<NetBean<BootImageBean>, GetBootImageApi>() {
                    @Override
                    public Observable<NetBean<BootImageBean>> invoke(GetBootImageApi getBootImageApi) {
                        return getBootImageApi.getBootImage();
                    }
                })
                .map(new NetBeanUnpackFunction<BootImageBean>() {
                    @NonNull
                    @Override
                    protected BootImageBean returnWhenDataNull() {
                        return BootImageBean.DEFAULT;
                    }
                })
                .map(new Function<BootImageBean, String>() {
                    @Override
                    public String apply(BootImageBean bootImageBean) throws Exception {
                        String url = bootImageBean.getUrl();
                        return StringUtil.isEmpty(url) ? "" : url;
                    }
                });
    }
}
