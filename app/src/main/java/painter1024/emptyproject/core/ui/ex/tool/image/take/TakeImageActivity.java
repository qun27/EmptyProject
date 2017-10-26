package painter1024.emptyproject.core.ui.ex.tool.image.take;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.IOException;

import painter1024.emptyptoject.lib_android.util.image.ImageUriUtil;
import painter1024.emptyptoject.lib_android.util.image.bitmap.BitmapSaveUtil;
import painter1024.emptyptoject.lib_android.util.intent.IntentUtil;
import io.reactivex.functions.Consumer;
import painter1024.emptyproject.R;
import painter1024.emptyproject.app.util.ResUtil;
import painter1024.emptyproject.core.ui.base.component.BaseActivity;
import painter1024.emptyproject.core.ui.ex.util.MsgUtil;

/**
 * 图片选择
 */

public class TakeImageActivity extends BaseActivity {
    private static final int REQUEST_TAKE_IMAGE_Gallery     = 1;//从画廊获取
    private static final int REQUEST_TAKE_IMAGE_CAMERA_S    = 2;//从相机获取小图
    private static final int REQUEST_TAKE_IMAGE_CAMERA_B    = 3;//从相机获取大图

    private Dialog dialog;
    private boolean needBigImage;//TODO 大图获取

    private int type = ImageTakeTool.TYPE_GALLERY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissionsAndInit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (dialog != null) {
            dialog.show();
        }
    }

    @Override
    protected void onStop() {
        if(dialog!=null){
            dialog.hide();
        }
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_TAKE_IMAGE_Gallery: {
                if (resultCode == RESULT_OK) {
                    handleGalleryImage(data);
                }
                break;
            } // REQUEST_TAKE_IMAGE_Gallery

            case REQUEST_TAKE_IMAGE_CAMERA_S: {
                if (resultCode == RESULT_OK) {
                    handleSmallCameraImage(data);
                }
                break;
            } // REQUEST_TAKE_IMAGE_CAMERA_S

            case REQUEST_TAKE_IMAGE_CAMERA_B: {
                if (resultCode == RESULT_OK) {
                    handleBigCameraImage(data);
                }
                break;
            } // REQUEST_TAKE_IMAGE_CAMERA_B
        } // switch
        finish();
    }

    private void checkPermissionsAndInit() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(
//                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            init();
                        } else {
                            MsgUtil.showMsg(ResUtil.getString(R.string.permission_photo_galley_fail));
                            finish();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        MsgUtil.showMsg(ResUtil.getString(R.string.permission_photo_galley_fail));
                        finish();
                    }
                });
    }

    private void init(){
        type = getIntent().getIntExtra(ImageTakeTool.KEY_TYPE, ImageTakeTool.TYPE_GALLERY);
        initView();
    }

    private void initView() {
        switch (type) {
            case ImageTakeTool.TYPE_GALLERY:
                openGallery();
                break;
            case ImageTakeTool.TYPE_CAMERA:
                openCamera();
                break;
            case ImageTakeTool.TYPE_ALL:
            default:
                openAll();
        }
    }

    private void openAll() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(R.array.image_get_mode, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        openCamera();
                        break;
                    case 1:
                        openGallery();
                        break;
                }
            }
        });

        dialog = builder.create();

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
//        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                finish();
//            }
//        });
    }

    /**
     * 开启相册
     */
    private void openGallery(){
        Intent intent = IntentUtil.prepareIntentChooseImageFromGallery();
        startActivityForResult(intent, REQUEST_TAKE_IMAGE_Gallery);
    }

    /**
     * 开启照相机
     */
    private void openCamera(){
        if(needBigImage){
            // TODO: 2017/2/7 请求获取大图
            return;
        }
        Intent intent = IntentUtil.prepareIntentChooseImageFromCamera();
        startActivityForResult(intent, REQUEST_TAKE_IMAGE_CAMERA_S);
    }

    private void handleGalleryImage(Intent intent) {
        if (intent != null) {
            String filepath;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                filepath = ImageUriUtil.getPicturePath(this, intent.getData());
            } else {
                filepath = ImageUriUtil.getPicturePath(this, intent.getData());
            }
            setImageResult(filepath);
        }
    }

    private void handleSmallCameraImage(Intent intent) {
        Bundle extras = intent.getExtras();
        Bitmap bitmap = (Bitmap) extras.get("data");
        //图片缓存路径
        String filepath = null;
        try {
            filepath = saveBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setImageResult(filepath);
    }

    private void handleBigCameraImage(Intent intent) {
        // TODO: 2017/2/7 处理大图返回数据
    }

    private String saveBitmap(Bitmap bitmap) throws IOException {
        File file = setUpImageFile();
        if(!BitmapSaveUtil.saveBitmap(bitmap, file)) throw new IOException("bitmap保存失败！");
        return file.getAbsolutePath();
    }

    private File setUpImageFile() throws IOException {
        File f = ImageFileUtil.createImageFile();
        return f;
    }

    private void setImageResult(String path){
        Intent intent = new Intent();
        intent.putExtra(ImageTakeTool.KEY_IMAGE_PATH, path);
        setResult(RESULT_OK, intent);
    }

}
