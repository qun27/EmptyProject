package painter1024.emptyproject.app.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.BoolRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import painter1024.emptyproject.app.MyApplication;


/**
 * 资源工具
 */
public class ResUtil {

    public static float getDimension(@DimenRes int id) {
        return MyApplication.getInstance().getResources().getDimension(id);
    }

    @NonNull
    public static String getString(int stringId) {
        return MyApplication.getInstance().getString(stringId);
    }

    @NonNull
    public static String getString(int stringId, Object... formatArgs) {
        return MyApplication.getInstance().getString(stringId, formatArgs);
    }

    public static int getColor(@ColorRes int color) {
        return ContextCompat.getColor(MyApplication.getInstance(), color);
    }

    public static boolean getBoolean(@BoolRes int bool) {
        return MyApplication.getInstance().getResources().getBoolean(bool);
    }

    public static int getInteger(@IntegerRes int integer) {
        return MyApplication.getInstance().getResources().getInteger(integer);
    }

    public static Drawable getDrawable(@DrawableRes int drawableRes) {
        return ContextCompat.getDrawable(MyApplication.getInstance(), drawableRes);
    }

    /**
     * 从属性中获取颜色，application样式
     *
     * @param color 属性颜色id
     * @return 属性相应颜色
     */
    public static int getColorOfAttr(int color) {
        return getColorOfAttr(MyApplication.getInstance(), color);
    }

    /**
     * 从属性中获取颜色，context对应样式
     *
     * @param color 属性颜色id
     * @return 属性相应颜色
     */
    public static int getColorOfAttr(Context context, int color) {
        int defaultColor = 0xFF000000;
        int[] attrsArray = {color};
        TypedArray typedArray = context.obtainStyledAttributes(attrsArray);
        int retColor = typedArray.getColor(0, defaultColor);
        // don't forget the resource recycling
        typedArray.recycle();
        return retColor;
    }
}
