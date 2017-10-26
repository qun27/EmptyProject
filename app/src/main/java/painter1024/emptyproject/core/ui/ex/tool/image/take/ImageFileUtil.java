package painter1024.emptyproject.core.ui.ex.tool.image.take;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片文件工具
 */

public class ImageFileUtil {
    private static final String JPEG_FILE_PREFIX = "IMG_";
    private static final String JPEG_FILE_SUFFIX = ".jpg";

    private static final String Thumbnail_FILE_PREFIX = "THUMBNAIL_";

    public static File createImageFile() throws IOException {
        // Create an image file name
        return createImageFile(JPEG_FILE_PREFIX, JPEG_FILE_SUFFIX);
    }

    public static File createThumbnailFile() throws IOException {
        // Create an image file name
        return createImageFile(Thumbnail_FILE_PREFIX, JPEG_FILE_SUFFIX);
    }

    public static File createImageFile(String prefix, String suffix) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = JPEG_FILE_PREFIX + timeStamp + "_";
        File albumF = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageF = File.createTempFile(imageFileName, JPEG_FILE_SUFFIX, albumF);
        return imageF;
    }
}
