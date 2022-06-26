package com.epam.spring.homework2.beans;

public class MyBean implements Validatable {
    protected String name;
    protected int value;

    public MyBean() {
    }

    public MyBean(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyBean{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void validate() {
        if (name != null && !name.isEmpty() && value > 0) {
            System.out.println("Bean " + name + " is valid");
        } else {
            System.out.println("Bean " + getClass().getSimpleName() + " is not valid");
        }
    }
}
