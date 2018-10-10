package com.java8.interface1;

public interface Defaulable {
    default String notRequired() {
        return "Default implementation";
    }
}
