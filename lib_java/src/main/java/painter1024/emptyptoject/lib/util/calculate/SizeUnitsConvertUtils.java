package painter1024.emptyptoject.lib.util.calculate;

import java.math.BigDecimal;

/**
 * size单位转换工具
 */
public class SizeUnitsConvertUtils {

    public static int B = 0;
    public static int KB = 1;
    public static int MB = 2;
    public static int GB = 3;
    public static int TB = 4;
    public static int PB = 5;

    public static String[] UNITS = {"B", "K", "M", "G", "T"};

    /***
     * @param from     从什么转化 B,KB,MB,GB,TB{@link #B},{@link #KB},{@link #MB},{@link #GB},{@link #TB}
     * @param to       转成什么格式 B,KB,MB,GB,TB{@link #B},{@link #KB},{@link #MB},{@link #GB},{@link #TB}
     * @param fileSize 文件的大小 根据from
     */
    @SuppressWarnings("BigDecimalMethodWithoutRoundingCalled")
    public static BigDecimal fileSizeConvert(int from, int to, double fileSize) {

        BigDecimal bigFileSize = BigDecimal.valueOf(fileSize);
        BigDecimal big1024 = BigDecimal.valueOf(1024);
        BigDecimal bigPow = big1024.pow(Math.abs(from - to));

        if (to > from) {
            return bigFileSize.divide(bigPow);
        } else if (to < from) {
            return bigFileSize.multiply(bigPow);
        } else {
            return new BigDecimal(fileSize);
        }
    }

    /**
     * @param from       从什么转化, {@link #B},{@link #KB},{@link #MB},{@link #GB},{@link #TB}
     * @param size   文件的大小 自动转换为最合适的单位
     * @param lowestUnit 最小单位,{@link #B},{@link #KB},{@link #MB},{@link #GB},{@link #TB}
     */
    public static SizeUnit fileFitSizeConvert(int from, double size, int lowestUnit) {
        SizeUnit sizeUnit;

        //如果提供的尺寸是0或者小于0，那么直接返回0值和最小单位
        if (size <= 0) {
            sizeUnit = new SizeUnit(BigDecimal.valueOf(0), lowestUnit);
            return sizeUnit;
        }

        //转化为B
        BigDecimal fileSize = fileSizeConvert(from, SizeUnitsConvertUtils.B, size);
        double fileSizeD = fileSize.doubleValue();
        int to = -1;
        BigDecimal big1024 = BigDecimal.valueOf(1024);
        if (fileSizeD < big1024.pow(KB).doubleValue()) {
            to = B;
        } else if (fileSizeD < big1024.pow(MB).doubleValue()) {
            to = KB;
        } else if (fileSizeD < big1024.pow(GB).doubleValue()) {
            to = MB;
        } else if (fileSizeD < big1024.pow(TB).doubleValue()) {
            to = GB;
        } else/* if (fileSizeD < big1024.pow(PB).doubleValue()) */{
            to = TB;
        }
        if(to < lowestUnit) to = lowestUnit;

        sizeUnit = new SizeUnit(fileSizeConvert(from, to, fileSize.doubleValue()), to);
        return sizeUnit;

    }

    /**
     * 获取单位对应文本
     * @param unit {@link #B},{@link #KB},{@link #MB},{@link #GB},{@link #TB}
     */
    public String getUnitStr(int unit){
        return UNITS[unit];
    }

    public static class SizeUnit {
        private BigDecimal fileSize;
        private int unit;

        public SizeUnit(BigDecimal fileSize, int unit) {
            this.fileSize = fileSize;
            this.unit = unit;
        }

        public SizeUnit() {
        }

        public BigDecimal getFileSize() {
            return fileSize;
        }

        public void setFileSize(BigDecimal fileSize) {
            this.fileSize = fileSize;
        }

        public int getUnit() {
            return unit;
        }

        public void setUnit(int unit) {
            this.unit = unit;
        }

        public String getUnitStr(){
            return UNITS[unit];
        }
    }
}
