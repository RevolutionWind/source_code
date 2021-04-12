package java8.function.collect;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author walker
 * @date 2020-07-10
 */
public class SortTest {
    public static void main(String[] args) {
        // 默认小 -> 大
        List<Integer> intList = Arrays.asList(6, 5, 4, 9, 3);
        intList.sort(Comparator.comparingInt(value -> value));
        intList.forEach(System.out::println);
        Integer integer = intList.stream().max(Integer::compareTo).get();
        System.out.println(integer);
    }
}
