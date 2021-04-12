package common;

import org.junit.Test;

import java.math.BigDecimal;

public class CommonTest {

    static Person getPerson() {
        Person 铁柱 = new Person("张三", 20, 1, "17236885676");
        Person 翠花 = new Person("翠花", 22, 0, "17832958347", 铁柱);
        return 翠花;
    }

    @Test
    public void testGetAndSet() {
        Person person = getPerson();
        Person lover = person.getLover();
        lover.setAge(25);
        lover.setName("已修改姓名");
        lover.setTelNum("已修改电话号");
        System.out.println(person);
    }

    @Test
    public void testBigDecimal() {
        BigDecimal decimal = BigDecimal.valueOf(11.10);
        BigDecimal trailingZeros = decimal.stripTrailingZeros();
        System.out.println(trailingZeros);
    }

}
