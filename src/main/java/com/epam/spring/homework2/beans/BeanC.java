package com.epam.spring.homework2.beans;

public class BeanC extends MyBean {

    public BeanC(String name, int value) {
        super(name, value);
    }

    public void customInitMethod() {
        System.out.println("Inside " + name + " customInitMethod()");
    }

    public void customDestroyMethod() {
        System.out.println("Inside " + name + " customDestroyMethod()");
    }
}
