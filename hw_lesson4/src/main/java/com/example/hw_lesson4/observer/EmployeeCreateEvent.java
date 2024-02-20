package com.example.hw_lesson4.observer;

import com.example.hw_lesson4.model.Employee;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class EmployeeCreateEvent extends ApplicationEvent {
    private Employee employee;
    public EmployeeCreateEvent(Object source, Employee employee) {
        super(source);
        this.employee = employee;



    }
}