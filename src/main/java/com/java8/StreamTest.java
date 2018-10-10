package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) {

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        //filter 方法用于通过设置的条件过滤出元素。
        List<String> filtered = strings.stream().
                filter(string->!string.equals("")).
                collect(Collectors.toList());
        System.out.println(filtered);
        //forEach来迭代流中的每个数据
        Random random = new Random();
        random.ints()
                .limit(10)//截取，limit 方法用于获取指定数量的流
                .sorted()//sorted 方法用于对流进行排序
                .forEach(System.out::println);
//        strings.forEach(s-> System.out.println(s));
        //map 方法用于映射每个元素到对应的结果
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> squaresList = numbers.stream()
                .map(t->t*t)
                .distinct()//去重
                .collect(Collectors.toList());
        System.out.println(squaresList);

        //并行（parallel）程序
        //parallelStream 是流并行处理程序的代替方法
        long count = strings.parallelStream().filter(string-> !string.equals("")).count();
        System.out.println(count);

    }
}
