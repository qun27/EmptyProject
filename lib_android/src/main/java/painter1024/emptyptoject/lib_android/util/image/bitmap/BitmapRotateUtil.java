package painter1024.emptyptoject.lib_android.util.image.bitmap;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.IOException;

/**
 * bitmap旋转工具
 */

public class BitmapRotateUtil {

    /**
     * 处理图片旋转。。。
     * @param image 需要旋转的图片
     * {@link ExifInterface#ORIENTATION_ROTATE_90 }<br/>
     * {@link ExifInterface#ORIENTATION_ROTATE_180 }<br/>
     * {@link ExifInterface#ORIENTATION_ROTATE_270 }<br/>
     */
    public static Bitmap rotateBitmap(Bitmap image, int rotateAngleRaw) {

        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.postRotate(rotateAngleRaw);
        Bitmap ret  = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, false);
//        if(recycleOld) image.recycle();//如果需要，回收旧图 , 不能这么做，回收旧图会同时回收掉create的新图，只有在真正不需要的时候回收
        return ret;
    }

    /**
     * 获取图片真实旋转角度
     */
    public static int getImageRotateAngleRaw(String filepath){
        int angleExif = getImageRotateAngleExif(filepath);
        return rotateAngleExif2Raw(angleExif);
    }

    /**
     * 将Exif旋转角度转化为真实旋转角度
     * @param rotateAngleExif 图片信息中得方向标志
     * @return 真实旋转角度
     *
     *
     * @see #getImageRotateAngleRaw(String)
     *
     * @see ExifInterface#ORIENTATION_ROTATE_90
     * @see ExifInterface#ORIENTATION_ROTATE_180
     * @see ExifInterface#ORIENTATION_ROTATE_270
     */
    public static int rotateAngleExif2Raw(int rotateAngleExif){
        int rotateAngleRaw = 0;
        if (rotateAngleExif == ExifInterface.ORIENTATION_ROTATE_90) {
            rotateAngleRaw= 90;
        } else if (rotateAngleExif == ExifInterface.ORIENTATION_ROTATE_180) {
            rotateAngleRaw = 180;
        } else if (rotateAngleExif == ExifInterface.ORIENTATION_ROTATE_270) {
            rotateAngleRaw = 270;
        }
        return rotateAngleRaw;
    }

    /**
     * 获取图片旋转度,角度以ExifInterface常量表示。
     * Reads Exif tags from the specified JPEG file.
     * @param filepath 图片路径
     * @return 图片的旋转角度的Exif值， 如果获取不到，默认返回{@link ExifInterface#ORIENTATION_NORMAL}
     *
     * @see #getImageRotateAngleRaw(String)
     * @see ExifInterface#ORIENTATION_ROTATE_90
     * @see ExifInterface#ORIENTATION_ROTATE_180
     * @see ExifInterface#ORIENTATION_ROTATE_270
     */
    public static int getImageRotateAngleExif(String filepath){
        ExifInterface exifInterface= null;
        try {
            exifInterface = new ExifInterface(filepath);
        } catch (IOException e) {
            e.printStackTrace();
            return ExifInterface.ORIENTATION_NORMAL;
        }
        //获取图片的旋转角度
        return exifInterface.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL);
    }
}
