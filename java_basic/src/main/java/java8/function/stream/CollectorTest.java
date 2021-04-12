package java8.function.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author walker
 * @date 2020-07-10
 */
public class CollectorTest {
    @Test
    public void testGroupingBy() {
        /*
        测试分组收集器
         */
        List<List<String>> lists = Arrays.asList(Arrays.asList("严良", "朱朝阳", "张东升"),
                Arrays.asList("XXX", "Juice", "DaBaby"), Arrays.asList("柯南", "灰原", "兰"));
        Map<Integer, List<String>> collect = lists.stream().flatMap(Collection::stream).collect(Collectors.groupingBy(String::length));
        System.out.println(collect);

        Map<Integer, String> collect1 = lists.stream().flatMap(Collection::stream)
                .collect(Collectors.groupingBy(String::length, Collectors.joining(",")));
        System.out.println(collect1);
    }

    @Test
    public void testToMap() {
        String str = "412|1,413|2,412|3";
        String[] split = str.split(",");
        Map<String, Integer> collect = Arrays.stream(split).map(couponInfo -> Arrays.asList(couponInfo.split("\\|")))
                .collect(Collectors.toMap(list -> list.get(0),
                        list -> Integer.parseInt(list.get(1)),
                        Integer::sum));
        collect.keySet().forEach(System.out::println);
        collect.values().forEach(System.out::println);
    }


}
