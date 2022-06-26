package com.epam.spring.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("Inside MyBeanFactoryPostProcessor:");
        System.out.println("BeanB uses default init method: " + configurableListableBeanFactory.getBeanDefinition("beanB").getInitMethodName());
        configurableListableBeanFactory.getBeanDefinition("beanB").setInitMethodName("newCustomInitMethod");
        System.out.println("BeanB changed init method: " + configurableListableBeanFactory.getBeanDefinition("beanB").getInitMethodName());
    }
}
