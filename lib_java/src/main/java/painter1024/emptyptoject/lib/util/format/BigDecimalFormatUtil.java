package painter1024.emptyptoject.lib.util.format;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 大数格式化工具
 */

public class BigDecimalFormatUtil {

    /**
     * @param bigDecimal   需要format的大数
     * @param decimalPlace 保留多少个小数点
     * @param roundingMode 小数点保留模式
     */
    public static String format(BigDecimal bigDecimal, int decimalPlace, RoundingMode roundingMode) {
        if (decimalPlace < 0) throw new IllegalArgumentException("decimalPlace 必须大于 0 ");
        bigDecimal = bigDecimal.setScale(decimalPlace, roundingMode);
        return bigDecimal.toString();
    }

    /**
     * 四舍五入的方式，保留多少位小数。
     */
    public static String formatHalfUp(BigDecimal bigDecimal, int decimalPlace) {
        return format(bigDecimal, decimalPlace, RoundingMode.HALF_UP);
    }

    /**
     * 保留多少位小数，忽略小数后一位
     */
    public static String formatDown(BigDecimal bigDecimal, int decimalPlace) {
        return format(bigDecimal, decimalPlace, RoundingMode.DOWN);
    }
}
