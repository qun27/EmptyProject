package painter1024.emptyptoject.lib.util.format;

import java.text.DecimalFormat;

import painter1024.emptyptoject.lib.util.calculate.SizeUnitsConvertUtils;

/**
 * 单位格式化工具
 */

public class SizeUnitsFormatUtils {

    /**
     * 转为合适单位，并根据指定格式进行格式化
     * @param from        从什么转化 B,KB,MB,GB,TB{@link SizeUnitsConvertUtils#B}
     * @param fileSize    文件的大小 自动转换为最合适的单位
     * @param formatStyle 需要格式化的样式{@link SizeUnitsConvertUtils#B}
     */
    public static String formatFit(int from, double fileSize, int lowestUnit, String formatStyle) {
        SizeUnitsConvertUtils.SizeUnit sizeUnit =
                SizeUnitsConvertUtils.fileFitSizeConvert(from, fileSize, lowestUnit);
        DecimalFormat format = new DecimalFormat(formatStyle);
        return format.format(sizeUnit.getFileSize().doubleValue()) + sizeUnit.getUnitStr();
    }

    /**
     * 可设置保留小数位数
     * @param from         从什么转化 B,KB,MB,GB,TB{@link SizeUnitsConvertUtils#B}
     * @param fileSize     文件的大小 自动转换为最合适的单位
     * @param decimalPlace 保留多少个小数点{@link SizeUnitsConvertUtils#B}
     */
    public static String formatFit(int from, double fileSize, int lowestUnit, int decimalPlace) {
        String formatStyle = "0";
        if (decimalPlace > 0) {
            formatStyle += ".";
            for (int i = 0; i < decimalPlace; i++) {
                formatStyle += "0";
            }
        }
        return formatFit(from, fileSize, lowestUnit, formatStyle);
    }
//    /**
//     * 可设置保留小数位数
//     * @param from         从什么转化 B,KB,MB,GB,TB{@link SizeUnitsConvertUtils#B}
//     * @param fileSize     文件的大小 自动转换为最合适的单位
//     * @param decimalPlace 保留多少个小数点{@link SizeUnitsConvertUtils#B}
//     */
//    public static String formatFit(int from, double fileSize, int lowestUnit, int decimalPlace) {
//        SizeUnitsConvertUtils.SizeUnit sizeUnit =
//                SizeUnitsConvertUtils.fileFitSizeConvert(from, fileSize, lowestUnit);
//        return BigDecimalFormatUtil.formatHalfUp(sizeUnit.getFileSize(), decimalPlace) + sizeUnit.getUnitStr();
//    }
}
