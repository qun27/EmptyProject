package painter1024.emptyptoject.lib_android.util.image.bitmap;

import android.graphics.Bitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * bitmap保存工具
 */

public class BitmapSaveUtil {

    /**
     * 保存bitmap
     */
    public static boolean saveBitmap(Bitmap bitmap, String path) {
        return saveBitmap(bitmap, new File(path), 100);
    }

    /**
     * 保存bitmap
     */
    public static boolean saveBitmap(Bitmap bitmap, File file) {
        return saveBitmap(bitmap, file, 100);
    }

    /**
     * 保存位图,同时根据质量指数进行压缩
     * @param bitmap    需要保存的位图
     * @param path      保存路径
     * @param quality   质量1-100
     * @return 是否保存成功
     */
    public static boolean saveBitmap(Bitmap bitmap, String path, int quality) {
        return saveBitmap(bitmap, new File(path), quality);
    }

    /**
     * 保存位图,同时根据质量指数进行压缩
     */
    public static boolean saveBitmap(Bitmap bitmap, File file, int quality) {
        if (bitmap == null) return false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, fos);
            fos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
