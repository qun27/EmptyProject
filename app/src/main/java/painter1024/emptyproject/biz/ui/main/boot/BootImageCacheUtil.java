package painter1024.emptyproject.biz.ui.main.boot;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import painter1024.emptyproject.app.MyApplication;
import painter1024.emptyproject.app.util.LogUtil;
import painter1024.emptyproject.biz.data.boot.store.sp.BootImageCacheSP;

/**
 * 图片加载工具
 */

public class BootImageCacheUtil {

    /**
     * 缓存图片
     * 有大小的就覆盖大小，没有直接缓存原图
     * @param url 图片地址
     * @param imageView 图片容器
     */
    public static void diskCacheImage(String url, ImageView imageView) {
        BootImageCacheSP.putBootImage(url);
        if (imageView == null || TextUtils.isEmpty(url)) {
            LogUtil.e("缓存图片失败，图片地址或者ImageView为空");
            return;
        }

        int width = imageView.getMeasuredWidth();
        int height = imageView.getMeasuredHeight();
        if (width <= 0 || height <= 0) {
            //无长宽，直接加载原图
            Glide.with(MyApplication.getInstance()).download(url).submit();
            return;
        }
        Glide.with(MyApplication.getInstance()).download(url).submit(width, height);
    }
}
