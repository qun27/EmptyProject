package painter1024.emptyptoject.lib.util.format;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.*;

public class BigDecimalFormatUtilTest {
    @Test
    public void format() throws Exception {
        assertEquals(BigDecimalFormatUtil.format(new BigDecimal(22.23033d), 2, RoundingMode.FLOOR), "22.23");
    }

    @Test
    public void formatHalfUp() throws Exception {
        assertEquals(BigDecimalFormatUtil.formatHalfUp(new BigDecimal(22.26633f), 1), "22.3");
    }

    @Test
    public void formatDown() throws Exception {
        assertEquals(BigDecimalFormatUtil.formatDown(new BigDecimal(22.23633d), 2), "22.23");
    }
}