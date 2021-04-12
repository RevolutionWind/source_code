package java8.function.lambda;


import java.util.ArrayList;
import java.util.List;

/**
 * @author walker
 * @date 2020-07-11
 */
public class LambdaTest {
    public static void main(String[] args) {
        /*
        lambda不能对外界可变的变量进行操作
         */
        String[] names = {"张三", "lisa", "nana"};
        List<Runnable> list = new ArrayList<>();
        for (String name : names){
            list.add(() -> System.out.println(name));
        }
//        for (int i=0;i<names.length;i++)
//        list.add(() -> System.out.println(names[i]));

    }
}
