package painter1024.emptyptoject.lib.util.format;

import org.junit.Test;

import painter1024.emptyptoject.lib.util.calculate.SizeUnitsConvertUtils;

import static org.junit.Assert.*;

public class SizeUnitsFormatUtilsTest {
    @Test
    public void formatFit() throws Exception {

    }

    @Test
    public void formatFit1() throws Exception {
        assertEquals("1.0K", SizeUnitsFormatUtils.formatFit(SizeUnitsConvertUtils.B, 1024, SizeUnitsConvertUtils.B, 1));
        assertEquals("2.0K", SizeUnitsFormatUtils.formatFit(SizeUnitsConvertUtils.B, 2048, SizeUnitsConvertUtils.B, 1));
        assertEquals("0.0B", SizeUnitsFormatUtils.formatFit(SizeUnitsConvertUtils.B, 0, SizeUnitsConvertUtils.B, 1));
        assertEquals("102.0B", SizeUnitsFormatUtils.formatFit(SizeUnitsConvertUtils.B, 102, SizeUnitsConvertUtils.B, 1));
        assertEquals("1.1K", SizeUnitsFormatUtils.formatFit(SizeUnitsConvertUtils.B, 1126, SizeUnitsConvertUtils.B, 1));

    }

}