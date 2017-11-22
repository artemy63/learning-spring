package org.artemy63.spring.ripper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleSpringApp {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("contextische.xml");
//        context.getBean(Quoter.class).sayQuote();
    }
}
