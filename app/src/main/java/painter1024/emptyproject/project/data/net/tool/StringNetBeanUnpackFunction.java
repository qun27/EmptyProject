package painter1024.emptyproject.project.data.net.tool;


import android.support.annotation.NonNull;

/**
 *  String 数据的 NetBean拆包Function
 */

public class StringNetBeanUnpackFunction extends NetBeanUnpackFunction<String> {

    @NonNull
    @Override
    protected String returnWhenDataNull() {
        return "";
    }
}
