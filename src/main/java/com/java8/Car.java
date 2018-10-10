package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用
 */
public class Car {

    public static Car create(Supplier<Car> supplier){
        return supplier.get();
    }

    public static void collide(final Car car){
        System.out.println("Collided "+ car.toString());
    }

    public  void follow(final  Car car){
        System.out.println("follow "+ car.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        //第一种方法引用的类型是构造器引用，语法是Class::new，
        // 或者更一般的形式：Class<T>::new。注意：这个构造器没有参数。
//        Supplier<Car> s = Car::new;
        Car car = create(Car::new);
        final List< Car > cars = Arrays.asList( car );
        //静态方法引用，语法是Class::static_method。注意：这个方法接受一个Car类型的参数
        cars.forEach(Car::collide);
        //第三种方法引用的类型是某个类的成员方法的引用，
        // 语法是Class::method，注意，这个方法没有定义入参：
        cars.forEach(Car::repair);

        cars.forEach(car::follow);

    }
}
