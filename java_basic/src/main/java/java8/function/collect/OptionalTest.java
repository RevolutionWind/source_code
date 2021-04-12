package java8.function.collect;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.IntStream;

public class OptionalTest {

    @Test
    public void testOrElseThrow() {
        Object o = Optional.ofNullable("null").orElseThrow(IllegalAccessError::new);
        System.out.println(o.toString());
        IntStream.rangeClosed(1, 0).forEach(System.out::println);
    }

    @Test
    public void test() {
        Object o = Optional.ofNullable("null").orElseGet(null);
        System.out.println(o);
    }
}
