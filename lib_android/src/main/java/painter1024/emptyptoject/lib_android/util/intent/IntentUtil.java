package painter1024.emptyptoject.lib_android.util.intent;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import java.io.File;

/**
 * intent相关工具
 */
public class IntentUtil {

    /**
     * 判断提供的Intent是否有可用，能被正常执行，有符合条件的接收者。
     * @param context 使用application context
     * @return Intent可用返回true
     */
    private static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        return intent.resolveActivity(packageManager) != null;
    }

    /**
     * 判断提供action，能被正常执行，有符合条件的接收者。
     * @param context 使用application context
     * @return Intent可用返回true
     */
    public static boolean isIntentAvailable(Context context, String action) {
        final Intent intent = new Intent(action);
        return isIntentAvailable(context, intent);
    }

    /**
     * 创建从图库中选择图片的Intent
     * @return 一个跳转到系统画廊，获取图片的Intent
     */
    public static Intent prepareIntentChooseImageFromGallery() {

        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        return intent;
    }

    /**
     * 创建相机选择图片的Intent，获取完整大图
     * @param saveFile 图片保存的文件索引
     * @return 一个跳转到系统相机，获取图片的Intent
     */
    public static Intent prepareIntentChooseImageFromCamera(File saveFile) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(saveFile));
        return intent;
    }

    /**
     * 创建相机选择图片的Intent，获取缩略图
     * @return 一个跳转到系统相机，获取图片的Intent
     */
    public static Intent prepareIntentChooseImageFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        return intent;
    }

    /**
     * 创建从手机相册获取图片的Intent，并进行切割
     * @param saveTo 保存地址
     * @param aspectX
     * @param aspectY
     * @param outputX
     * @param outputY
     * @param returnData
     * @deprecated 有些定制系统不支持
     */
    public static Intent prepareIntentChooseImageFromCamera(Uri saveTo, int aspectX, int aspectY,
                                                int outputX, int outputY, boolean returnData) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", saveTo);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        return intent;
    }

    /**
     * 获取系统显示文件夹
     * @param title 系统弹框的标题
     * */
    public static Intent prepareIntentGetSystemFile(Context context, String title) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // special intent for Samsung file manager ,专门给三星用的特殊Intent
        Intent sIntent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
        // if you want any file type, you can skip next line
        sIntent.putExtra("CONTENT_TYPE", "*/*");
        sIntent.addCategory(Intent.CATEGORY_DEFAULT);

        Intent chooserIntent;
        //判断是否存在符合条件的Activity
        if (context.getApplicationContext().getPackageManager().resolveActivity(sIntent, 0) != null) {
            // it is device with samsung file manager
            chooserIntent = Intent.createChooser(sIntent, title);
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intent});
        } else {
            chooserIntent = Intent.createChooser(intent, title);
        }
        return chooserIntent;
    }


}
