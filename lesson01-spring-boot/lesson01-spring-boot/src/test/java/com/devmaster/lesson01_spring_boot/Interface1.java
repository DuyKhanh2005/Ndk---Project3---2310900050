package com.devmaster.lesson01_spring_boot;

public interface Interface1 {
    default void method1() {
        System.out.println("Interface1, method1");
    }

    interface Interface2 {
        default void method2() {
            System.out.println("Interface2, method2");
        }
    }
}
