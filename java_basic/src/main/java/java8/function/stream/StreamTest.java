package java8.function.stream;

import common.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author walker
 * @date 2020-07-10
 */
public class StreamTest {

    @Test
    public void testEmptyList() {
        /*
        测试空集合是否报错
        空集合进行stream会返回空，不会报NPE
         */
        List<Person> emptyPersonList = new ArrayList<>(10);
        List<String> names = emptyPersonList.stream().map(Person::getName).collect(Collectors.toList());
        names.forEach(System.out::println);
    }

    @Test
    public void testFilter() {
        /*
        测试filter
        true: 不会被过滤
        false: 会被过滤掉
         */
        List<String> stringList = Arrays.asList("1", "2", "3");
        List<String> collect = stringList.stream().filter("1"::equals).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    @Test
    public void testMergeTwoList() {
        /*
        测试合并两个集合
         */
        List<Person> targetList = new ArrayList<>(5);
        List<Person> personList1 = getPersonList(1);
        List<Person> personList2 = getPersonList(2);
        personList1.addAll(personList2);
        Map<String, Set<Person>> collect = personList1.stream().collect(
                Collectors.groupingBy(Person::getName, Collectors.toSet()));
    }

    @Test
    public void testFindLongestWord() {
        /*
        从单词集合中找到最长的那个单词
         */
        List<String> generateWords = generateWords();
        Optional<String> reduce = generateWords.stream().reduce((w1, w2) -> {
            if (w1.length() > w2.length())
                return w1;
            return w2;
        });
        reduce.ifPresent(System.out::println);

        Optional<String> max = generateWords.stream().max((w1, w2) -> {
            if (w1.length() > w2.length())
                return 1;
            return 0;
        });
        max.ifPresent(System.out::println);
    }

    @Test
    public void testIntStream() {
        /*
        获取一个int类型的流
         */
        int[] values = {1, 4, 9, 16};
        Stream<int[]> ints = Stream.of(values);
        IntStream intStream = ints.flatMapToInt(Arrays::stream);
        long count = intStream.count();
        System.out.println(count);
    }

    /*
    测试分页
     */
    @Test
    public void testPage() {
        int pageNum = 1;
        int pageSize = 5;
        List<Integer> integerList = Stream.iterate(1, n -> n + 1).limit(30).collect(Collectors.toList());
        List<Integer> results = integerList.subList((pageNum * pageSize), (pageSize * (pageNum + 1)));
        results.forEach(System.out::println);
    }

    private static List<Person> getPersonList(Integer choice) {
        switch (choice) {
            case 1:
                return Arrays.asList(new Person("john", 23, null, "123456"),
                        new Person("rose", 23, 0, "12356756"));
            case 2:
                return Arrays.asList(new Person("john", null, 1, "123456"),
                        new Person("rose", 26, 1, ""));
        }
        throw new IllegalArgumentException("非法输入");
    }

    private static List<String> generateWords() {
        return Arrays.asList("john snow", "卡丽西", "Russ");
    }

}
