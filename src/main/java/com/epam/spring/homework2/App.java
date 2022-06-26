package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.BeanB;
import com.epam.spring.homework2.config.AppConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(context.getBean(beanName));
        }
        context.close();
    }
}
