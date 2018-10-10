package com.java8;

import com.java8.interface1.MathOperation;
import com.java8.interface1.MathTest;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaTest1 {

    public static void main(String[] args) {
        int x =4;
        int y = 3;
        //直接声明接口实现，不声明类型
        MathOperation operation = (a,b) -> a+b;
        operation.operation(x,y);
        MathTest mathTest1 = new MathTest(
                operation);
        //用大括号
        MathTest mathTest = new MathTest(
                (w,s)->{return w-s;});
        //声明类型
        MathTest mathTest2  = new MathTest((int a,int b)->a*b);
        int res = mathTest.operate(4,5);

        String[] players = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        // 1.1 使用匿名内部类根据 name 排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });
        // 1.2 使用 lambda expression 排序 players
        Comparator<String> comparator = (String t1,String t2)->t1.compareTo(t2);

        System.out.println(res);
        Arrays.sort(players,(String s1,String s2)->{return s1.compareTo(s2);});

        Arrays.asList("1","j","5","t","2","f").forEach(e-> System.out.println(e));



    }
}
