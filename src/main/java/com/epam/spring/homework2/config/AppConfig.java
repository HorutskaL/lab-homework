package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;


@Configuration
@PropertySource("application.properties")
@Import(AppConfigAnother.class)
public class AppConfig {

    @Value("${BeanB.name}")
    private String beanBName;
    @Value("${BeanB.value}")
    private int beanBValue;

    @Value("${BeanC.name}")
    private String beanCName;
    @Value("${BeanC.value}")
    private int beanCValue;

    @Value("${BeanD.name}")
    private String beanDName;
    @Value("${BeanD.value}")
    private int beanDValue;

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    public BeanD beanD() {
        return new BeanD(beanDName, beanDValue);
    }

    @Bean(initMethod = "newCustomInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanD")
    public BeanB beanB() {
        return new BeanB(beanBName, beanBValue);
    }

    @Bean(initMethod = "customInitMethod", destroyMethod = "customDestroyMethod")
    @DependsOn("beanB")
    public BeanC beanC() {
        return new BeanC(beanCName, beanCValue);
    }


}
