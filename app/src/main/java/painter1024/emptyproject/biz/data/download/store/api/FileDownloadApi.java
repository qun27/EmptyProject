package painter1024.emptyproject.biz.data.download.store.api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 文件下载api
 */

public interface FileDownloadApi {
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}
