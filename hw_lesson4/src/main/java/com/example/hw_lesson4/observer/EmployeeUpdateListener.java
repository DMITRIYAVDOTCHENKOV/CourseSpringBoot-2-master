package com.example.hw_lesson4.observer;

import com.example.hw_lesson4.servicec.FileGateWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeUpdateListener implements ApplicationListener<EmployeeCreateEvent> {
    @Autowired
    FileGateWay fileGateWay;
    @Override
    public void onApplicationEvent(EmployeeCreateEvent event) {
        fileGateWay.writeToFile("D:/Java/java_Project/CourseSpringBoot-2-master/addUsers_log.txt", event.getEmployee().getLastName());
        System.out.println("Добавлен пользователь: " + event.getEmployee().toString());
    }
}
