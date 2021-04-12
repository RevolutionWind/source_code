package java8.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAdjusters;

/**
 * @author walker
 * @date 2020-07-15
 */
public class TestTimeUtis {

    /*
    一般不需要时区的计算
     */
    @Test
    public void testInstant() throws InterruptedException {
        Instant time1 = Instant.now();
        Thread.sleep(10000L);
        Instant time2 = Instant.now();
        Duration between = Duration.between(time1, time2);
        long seconds = between.getSeconds();
        System.out.println(seconds);
    }

    /*
    测试本地时间
     */
    @Test
    public void testLocalDate() {
        LocalDate localDate = LocalDate.of(2020, Month.JULY, 15);
        LocalDate date1 = localDate.plusDays(10).plusWeeks(1);
        System.out.println(date1);
        // 日期计算必须是相同单位
//        Duration period = Duration.ofMillis(1000000000L);
        Period period = Period.ofDays(12);
        LocalDate date2 = localDate.plus(period);
        System.out.println(date2);
        DayOfWeek date3 = localDate.getDayOfWeek();
        System.out.println(date3);
        /*
        日期矫正器
         */
        LocalDate date4 = localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        LocalDate date5 = localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY));
        System.out.println(date4 + ":" + date5);
        LocalDate date6 = localDate.with(w -> {
            LocalDate result = (LocalDate) w;
            while (result.getDayOfWeek().getValue() > 1) {
                result = result.plusDays(1L);
            }
            return result;
        });
        System.out.println(date6);
    }

    /*
    测试本地时间
     */
    @Test
    public void testLocalTime() {
        LocalTime localTime = LocalTime.of(20, 23, 59);
        LocalTime time1 = localTime.plusHours(5L);
        System.out.println(time1);
        // 增加纳秒
        LocalTime time2 = localTime.plusNanos(1000L);
        System.out.println(time2);
        System.out.println(time1.isAfter(time2));
    }

    /*
    测试时间与字符串之间格式化
     */
    @Test
    public void testTrans(){
        LocalDateTime time3 = LocalDateTime.of(2020, Month.JULY, 15, 20, 43);
        System.out.println(time3);
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss").format(time3);
        System.out.println(format);
    }
}
