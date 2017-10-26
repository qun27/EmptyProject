package painter1024.emptyptoject.lib.util.calculate;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class SizeUnitsConvertUtilsTest {
    @Test
    public void fileSizeConvert() throws Exception {
        BigDecimal decimal;
        decimal = SizeUnitsConvertUtils.fileSizeConvert(SizeUnitsConvertUtils.KB,
                SizeUnitsConvertUtils.KB, 1);
        assertEquals(decimal.doubleValue(), 1d, 0);
        decimal = SizeUnitsConvertUtils.fileSizeConvert(SizeUnitsConvertUtils.B,
                SizeUnitsConvertUtils.B, 1);
        assertEquals(decimal.doubleValue(), 1d, 0);
        decimal = SizeUnitsConvertUtils.fileSizeConvert(SizeUnitsConvertUtils.KB,
                SizeUnitsConvertUtils.B, 1);
        assertEquals(decimal.doubleValue(), 1024d, 0);
    }

    @Test
    public void fileFitSizeConvert() throws Exception {
        SizeUnitsConvertUtils.SizeUnit d;
        d = SizeUnitsConvertUtils.fileFitSizeConvert(SizeUnitsConvertUtils.B,
                100, SizeUnitsConvertUtils.B);
        assertEquals(SizeUnitsConvertUtils.B, d.getUnit(), 0);
        assertEquals(100, d.getFileSize().doubleValue(), 0);

        d = SizeUnitsConvertUtils.fileFitSizeConvert(SizeUnitsConvertUtils.B,
                1024, SizeUnitsConvertUtils.B);
        assertEquals(SizeUnitsConvertUtils.KB, d.getUnit(), 0);
        assertEquals(1, d.getFileSize().doubleValue(), 0);

        d = SizeUnitsConvertUtils.fileFitSizeConvert(SizeUnitsConvertUtils.B,
                1024*1024, SizeUnitsConvertUtils.B);
        assertEquals(SizeUnitsConvertUtils.MB, d.getUnit(), 0);
        assertEquals(1, d.getFileSize().doubleValue(), 0);

        d = SizeUnitsConvertUtils.fileFitSizeConvert(SizeUnitsConvertUtils.B,
                1024*1024*1024, SizeUnitsConvertUtils.B);
        assertEquals(SizeUnitsConvertUtils.GB, d.getUnit(), 0);
        assertEquals(1, d.getFileSize().doubleValue(), 0);
    }

    @Test
    public void getUnitStr() throws Exception {

    }

    @org.junit.Test
    public void testConvertGBToMB() throws Exception {
        BigDecimal d = SizeUnitsConvertUtils.fileSizeConvert(SizeUnitsConvertUtils.GB, SizeUnitsConvertUtils.MB, 1);
        assertEquals(d.doubleValue(), 1024, 0);
    }

    @org.junit.Test
    public void testConvertTBToMB() throws Exception {
        BigDecimal d = SizeUnitsConvertUtils.fileSizeConvert(SizeUnitsConvertUtils.TB, SizeUnitsConvertUtils.MB, 1);
        assertEquals(d.doubleValue(), (1024 * 1024), 0);
    }

    @org.junit.Test
    public void testConvertTBToKB() throws Exception {
        BigDecimal d = SizeUnitsConvertUtils.fileSizeConvert(SizeUnitsConvertUtils.TB, SizeUnitsConvertUtils.KB, 1);
        assertEquals(d.doubleValue(), (1024 * 1024 * 1024), 0);
    }

    @org.junit.Test
    public void testConvertMBToGB() throws Exception {
        BigDecimal d = SizeUnitsConvertUtils.fileSizeConvert(SizeUnitsConvertUtils.MB, SizeUnitsConvertUtils.GB, 1000);
        assertEquals(d.doubleValue(), (((double) 1000) / ((double) 1024)), 0);
    }


    @org.junit.Test
    public void testConvertMBToB() throws Exception {
        BigDecimal d = SizeUnitsConvertUtils.fileSizeConvert(SizeUnitsConvertUtils.MB, SizeUnitsConvertUtils.B, 10);
        assertEquals(d.doubleValue(), (10 * 1024 * 1024), 0);
    }

}