package painter1024.emptyproject.biz.ui.util;

import painter1024.emptyptoject.lib.util.calculate.SizeUnitsConvertUtils;
import painter1024.emptyptoject.lib.util.format.SizeUnitsFormatUtils;

/**
 * 尺寸格式工具
 */

public class SizeFormatUtil {

    /**
     * 从B单位转为合适单位，最低为MB，最终格式化保留1位小数
     */
    public static String formatSize(long size) {
        return SizeUnitsFormatUtils.formatFit(SizeUnitsConvertUtils.B, size, SizeUnitsConvertUtils.MB, 1);
    }

}
