package painter1024.emptyptoject.lib.util.format;

import java.util.Arrays;
import java.util.List;

import painter1024.emptyptoject.lib.util.base.StringUtil;

/**
 * 容器格式化工具
 */

public class CollectionsFormatUtil {

    /**
     * 使用指定分隔符拼接指定对象列表
     * */
    public static <T>String formatBySeparator(List<T> list, String separator) {
        String formatComma = null;
        //判断为空直接返回
        if (list == null || list.size() == 0 || StringUtil.isEmpty(separator))
            return "";

        //字符拼接
        for (int i = 0; i < list.size(); i++) {
            T item = list.get(i);
            if(item==null) continue; //为空，跳过

            if(formatComma==null) {
                formatComma = "" + item;
            } else {
                formatComma = formatComma + separator + item;
            }
        }
        return formatComma;
    }

    /**
     * 根据指定分隔符切割文本
     * @param str 源文本
     * @param regularExpression 分隔符
     */
    public static String[] unFormatBySeparator(String str, String regularExpression) {
        if(str==null || StringUtil.isEmpty(str) || regularExpression==null
                || StringUtil.isEmpty(regularExpression)) return null;

        String[] ret = str.split(regularExpression);
        return ret;
    }


    /**
     * 拼接带逗号的文本，即是aa,bb,cc
     * */
    public static <T>String formatByComma(List<T> list) {
        return formatBySeparator(list, ",");
    }

    /**
     * 拼接带逗号的文本，即是aa,bb,cc
     * */
    public static <T>String formatByComma(T... array) {
        return formatByComma(Arrays.asList(array));
    }

    /**
     * 切割带逗号的文本
     */
    public static String[] unFormattedCommaString(String str) {
        return unFormatBySeparator(str, ",");
    }
}
