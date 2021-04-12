package amount;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author walker
 */
@SuppressWarnings("unused")
public class TestBigDecimal {
    @Test
    public void testDivide() {
        // 向下取整
        System.out.println(BigDecimal.valueOf(1).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_DOWN));
        LocalDateTime dateTime = LocalDateTime.of(2020, 8, 5, 16, 26, 11);
        System.out.println(LocalDateTime.now().isAfter(dateTime));

    }
}
