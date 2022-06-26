package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.BeanA;
import com.epam.spring.homework2.beans.BeanE;
import com.epam.spring.homework2.beans.BeanF;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class AppConfigAnother {
    @Value("beanAByDefault")
    private String beanAName;
    @Value("11")
    private int beanAValue;

    @Value("beanEByDefault")
    private String beanEName;
    @Value("-12")
    private int beanEValue;

    @Value("beanFByDefault")
    private String beanFName;
    @Value("13")
    private int beanFValue;

    @Bean
    public BeanA beanA() {
        return new BeanA(beanAName, beanAValue);
    }

    @Bean
    public BeanE beanE() {
        return new BeanE(beanEName, beanEValue);
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF(beanFName, beanFValue);
    }
}
