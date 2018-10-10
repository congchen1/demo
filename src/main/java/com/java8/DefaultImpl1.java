package com.java8;

import com.java8.interface1.Defaulable;

public class DefaultImpl1 implements Defaulable {
    @Override
    public String notRequired() {
        return "DefaultImpl1";
    }


    public static void main(String[] args) {
        Defaulable defaulable = new DefaultImpl1();
        System.out.println(defaulable.notRequired());
    }
}
