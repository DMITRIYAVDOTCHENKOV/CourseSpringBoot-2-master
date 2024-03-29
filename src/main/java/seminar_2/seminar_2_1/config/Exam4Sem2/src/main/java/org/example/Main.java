package org.example;

import org.example.services.CommentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var c = new AnnotationConfigApplicationContext(seminar_2.seminar_2_1.config.Exam4Sem2.src.main.java.org.example.config.ProjectConfig.class);

        var cs1 = c.getBean("commentService", CommentService.class);
        var cs2 = c.getBean("commentService", CommentService.class);
        // System.out.println("Hello world!");

        boolean b1 = cs1 == cs2;
        System.out.println(b1);
        System.out.println(cs1);
        System.out.println(cs2);
    }
}