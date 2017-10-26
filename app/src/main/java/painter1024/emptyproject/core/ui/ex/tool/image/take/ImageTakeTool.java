package painter1024.emptyproject.core.ui.ex.tool.image.take;

import android.content.Context;
import android.content.Intent;

/**
 * 图片获取工具
 */

public class ImageTakeTool {
    public static final String KEY_IMAGE_PATH = "KEY_IMAGE_PATH";
    public static final String KEY_TYPE = "KEY_TYPE";

    public static final int TYPE_GALLERY    = 0;
    public static final int TYPE_CAMERA     = 1;
    public static final int TYPE_ALL        = 2;

    /**
     * 准备一个获取图片的Intent
     * @return 一个获取图片的Intent
     */
    public static Intent prepareIntentImageTake(Context context) {
        Intent intent = new Intent(context, TakeImageActivity.class);
        return intent;
    }

    /**
     * 准备一个获取图片的Intent
     * @param type {@link #TYPE_GALLERY}, {@link #TYPE_CAMERA}, {@link #TYPE_ALL}
     * @return 一个获取图片的Intent
     */
    public static Intent prepareIntentImageTake(Context context, int type) {
        Intent intent = prepareIntentImageTake(context);
        intent.putExtra(KEY_TYPE, type);
        return intent;
    }

    /**
     * 解析图片路径
     * @param intent 请求图片后，在onActivityResult(int, int, Intent)返回的intent
     * @return 解析得到的图片路径
     * @see #prepareIntentImageTake(Context)
     */
    public static String resolveImagePath(Intent intent) {
        return intent.getStringExtra(KEY_IMAGE_PATH);
    }
}
