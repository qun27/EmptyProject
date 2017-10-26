package painter1024.emptyptoject.lib_android.util.image.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * bitmap解析工具
 */

public class BitmapDecodeUtil {
    /**
     * 从Resourec中加载图片
     * @param res 资源对象
     * @param resId  资源id
     * @param reqWidth 请求的宽度
     * @param reqHeight 请求的高度
     * @return 得到的图片
     */
    public static Bitmap decodeBitmapFromResource(Resources res, int resId,
                                                  int reqWidth, int reqHeight) {

        // BEGIN_INCLUDE (read_bitmap_dimensions)
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // END_INCLUDE (read_bitmap_dimensions)


        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 从路径中记载图片
     * @param filePath 图片完整路径
     * @param reqWidth 请求的宽度
     * @param reqHeight 请求的高度
     * @return 获得的图片
     */
    public static Bitmap decodeBitmapFromPath(String filePath, int reqWidth, int reqHeight) {

        // BEGIN_INCLUDE (read_bitmap_dimensions)
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // END_INCLUDE (read_bitmap_dimensions)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 从路径中记载图片
     * @param filePath 图片完整路径
     * @param reqWidth 请求的宽度
     * @return 获得的图片
     */
    public static Bitmap decodeBitmapFromPathByWidth(String filePath, int reqWidth) {

        // BEGIN_INCLUDE (read_bitmap_dimensions)
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSizeByWidth(options, reqWidth);
        // END_INCLUDE (read_bitmap_dimensions)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * 从描述符号中获取图片
     * @param fileDescriptor 文件描述符
     * @param reqWidth 请求的宽度
     * @param reqHeight 请求的高度
     * @return
     */
    public static Bitmap decodeBitmapFromDescriptor(
            FileDescriptor fileDescriptor, int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
    }

    /**
     * 根据宽度计算缩放比
     * @param options
     * @param reqWidth
     * @return
     */
    public static int calculateInSampleSizeByWidth(BitmapFactory.Options options,
                                                   int reqWidth) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        inSampleSize = width/reqWidth;
        //i("width=" + width + "reqWidth=" + reqWidth + "计算后的缩放比inSampleSize=" + inSampleSize);
        return inSampleSize;
    }

    /**
     * 根据原图Options 和 目标长宽，计算图片的缩放值
     * 如果图片的原始高度或者宽度大与我们期望的宽度和高度，我们需要计算出缩放比例的数值。否则就不缩放。

     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // BEGIN_INCLUDE (calculate_sample_size)
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }

            // This offers some additional logic in case the image has a strange
            // aspect ratio. For example, a panorama may have a much larger
            // width than height. In these cases the total pixels might still
            // end up being too large to fit comfortably in memory, so we should
            // be more aggressive with sample down the image (=larger inSampleSize).

            //屏蔽原因，压缩压太过了，出现模糊
            /**long totalPixels = width * height / inSampleSize;

             // Anything more than 2x the requested pixels we'll sample down further
             final long totalReqPixelsCap = reqWidth * reqHeight * 2;

             while (totalPixels > totalReqPixelsCap) {
             inSampleSize *= 2;
             totalPixels /= 2;
             }*/
        }
        //i("width=" + width + "reqWidth=" + reqWidth + "计算后的缩放比inSampleSize=" + inSampleSize);
        return inSampleSize;
        // END_INCLUDE (calculate_sample_size)
    }

}
