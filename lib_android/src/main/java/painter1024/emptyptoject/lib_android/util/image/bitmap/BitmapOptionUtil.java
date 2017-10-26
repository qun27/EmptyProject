package painter1024.emptyptoject.lib_android.util.image.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.ThumbnailUtils;

/**
 * bitmap操作工具
 */

public class BitmapOptionUtil {

    /**
     * @see ThumbnailUtils#createVideoThumbnail(String, int)
     */
    public static Bitmap createVideoThumbnail(String filePath, int kind) {
        return ThumbnailUtils.createVideoThumbnail(filePath, kind);
    }

    /**
     * @see ThumbnailUtils#extractThumbnail(Bitmap, int, int)
     */
    public static Bitmap extractThumbnail(Bitmap source, int width, int height) {
        return ThumbnailUtils.extractThumbnail(source, width, height);
    }

    /**
     * @see ThumbnailUtils#extractThumbnail(Bitmap, int, int, int)
     */
    public static Bitmap extractThumbnail(Bitmap source, int width, int height, int options) {
        return ThumbnailUtils.extractThumbnail(source, width, height, options);
    }

    /**
     * 创建位图缩略
     * @param bitMap  原图
     * @param needRecycle 需要回收原图
     * @deprecated  使用 {@link #extractThumbnail(Bitmap, int, int)}  }
     */
    public static Bitmap createBitmapThumbnail(Bitmap bitMap, boolean needRecycle) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想要的大小
        int newWidth = 120;
        int newHeight = 120;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height,
                matrix, true);
        if (needRecycle) bitMap.recycle();
        return newBitMap;
    }

    /**
     * scale image
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight, boolean recycleOld) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight(), recycleOld);
    }

    /**
     * scale image
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight, boolean recycleOld) {
        if (org == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap ret = Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
        if(recycleOld) org.recycle();//如果需要，回收旧图
        return ret;
    }

    /**
     * 转为圆角
     * @param bitmap
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, boolean recycleOld) {
        int height = bitmap.getHeight();
        int width = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xff424242);
        //paint.setColor(Color.TRANSPARENT);
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        if(recycleOld) bitmap.recycle();
        return output;
    }
}
