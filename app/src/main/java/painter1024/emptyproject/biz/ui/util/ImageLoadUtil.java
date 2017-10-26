package painter1024.emptyproject.biz.ui.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

/**
 *
 */

public class ImageLoadUtil {
    public enum ImageType{
        /**
         * 头像
         */
        HEAD,
        /**
         * 其他
         */
        OTHER
    }

    /**
     * 图片加载
     * @param imageView 需要加载图片的ImageView
     * @param string A file path, or a uri
     * @param type 加载的图片类型
     */
    public static void load(ImageView imageView, String string, ImageType type) {
        int id = getImageResIdByType(type);
        load(imageView, string, id);
    }

    private static int getImageResIdByType(ImageType type) {
        // TODO 相应真实资源
//        int id = 默认占位图;
//        switch (type){
//            case HEAD:
//                id = 头像占位图;
//                break;
//        }
//        return id;
        return 0;
    }

    /**
     * 图片加载
     * @param imageView 需要加载图片的ImageView
     * @param string A file path, or a uri
     * @param defId 默认图id，包括默认图和异常图
     */
    public static void load(ImageView imageView, String string, int defId) {
        load(imageView, string, defId, defId);
    }

    /**
     * 图片加载
     * @param imageView 需要加载图片的ImageView
     * @param string A file path, or a uri
     * @param placeholderId 占位图
     * @param errorId 异常图
     */
    public static void load(ImageView imageView, String string, int placeholderId, int errorId) {
        Glide.with(imageView.getContext())
                .load(string)
                .apply(RequestOptions.placeholderOf(placeholderId).error(errorId).dontAnimate())
                .into(imageView);
    }

}
