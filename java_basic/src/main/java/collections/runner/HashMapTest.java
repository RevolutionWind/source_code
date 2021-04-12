package collections.runner;

import collections.HashMapByMe7;

/**
 * @author walker
 * @date 2020-07-01
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMapByMe7<String, String> hashMapByMe7 = new HashMapByMe7<>(16);
        hashMapByMe7.put("1", "1");
        hashMapByMe7.put("2", "1");
        hashMapByMe7.put("3", "1");
        hashMapByMe7.put("4", "1");
        hashMapByMe7.put("5", "1");
        hashMapByMe7.put("6", "1");
        hashMapByMe7.put("7", "1");
        hashMapByMe7.put("8", "1");
        hashMapByMe7.put("9", "1");
        hashMapByMe7.put("10", "1");
        hashMapByMe7.put("11", "1");
        hashMapByMe7.put("12", "1");
        hashMapByMe7.put("13", "1");
        hashMapByMe7.put("14", "1");
        String s = hashMapByMe7.get("13");
        System.out.println(s);
    }

}
