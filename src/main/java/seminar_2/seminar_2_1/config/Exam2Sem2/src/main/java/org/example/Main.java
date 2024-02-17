package org.example;

import org.example.domain.Car;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
       // System.out.println("Hello world!");
        var context = new AnnotationConfigApplicationContext(ProcessBuilder.class);

        Car c = context.getBean(Car.class);
        org.example.domain.Engin e = context.getBean(org.example.domain.Engin.class);

        System.out.println(c);
        System.out.println(e);
    }
}