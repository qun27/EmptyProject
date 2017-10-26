package painter1024.emptyproject.biz.data.boot.store.api;

import io.reactivex.Observable;
import painter1024.emptyproject.project.data.net.NetBean;
import painter1024.emptyproject.biz.data.boot.store.model.BootImageBean;
import retrofit2.http.POST;

/**
 * 获取启动页图片
 */

public interface GetBootImageApi {
    @POST("sign/index/get-image")
    Observable<NetBean<BootImageBean>> getBootImage();
}
