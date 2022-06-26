package com.epam.spring.homework2.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE extends MyBean {
    public BeanE(String name, int value) {
        super(name, value);
    }

    @PostConstruct
    public void customPostConstructMethod() {
        System.out.println("Inside " + name + " customPostConstructMethod()");
    }

    @PreDestroy
    public void customPreDestroyMethod() {
        System.out.println("Inside " + name + " customPreDestroyMethod()");
    }
}
