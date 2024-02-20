package com.example.hw_lesson4.aspect;


import com.example.hw_lesson4.model.Employee;
import com.example.hw_lesson4.observer.EmployeeCreateEvent;
import com.example.hw_lesson4.servicec.FileGateWay;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;



@Aspect
@Component
public class UserActionAspect implements ApplicationListener<EmployeeCreateEvent> {
    @Autowired
    FileGateWay fileGateWay;
    Employee employee;
    @Override
    public void onApplicationEvent(EmployeeCreateEvent event) {
        fileGateWay.writeToFile("D:/Java/java_Project/CourseSpringBoot-2-master/addUsers_log.txt", event.getEmployee().getLastName());
        writeToTextFile("Добавлен пользователь: " + event.getEmployee().toString(), "user_actions_log.txt");
    }

    private void writeToTextFile(String logMessage, String fileName) {
        try {
            fileGateWay.writeToFile(fileName, logMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Before("@annotation(com.example.hw_lesson4.aspect.TrackUserAction)")
    public void trackUserAction(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String logMessage = "User action: Method " + methodName + " called with args " + Arrays.deepToString(args);
        System.out.println(logMessage);

        // Логирование полной информации о добавлении или удалении Employee
        if (methodName.equals("saveEmployee")) {
            Employee employee = (Employee) args[0];
            logMessage = "User action: Employee " + employeeToString(employee) + " added";
        } else if (methodName.equals("deleteEmployee")) {
            Long employeeId = (Long) args[0];
            logMessage = "User action: Employee with id " + employeeId + " deleted";
        }

        // Запись в текстовый файл
        String fileName = "user_actions_log.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(logMessage);
            writer.newLine();
            
            onApplicationEvent(employee);

        } catch (IOException e) {
            // Обработка ошибок записи в файл
            e.printStackTrace();
        }

    }

    private void onApplicationEvent(Employee employee) {
    }

    private String employeeToString(Employee employee) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = Employee.class.getDeclaredFields();
        sb.append("{");
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                sb.append(field.getName()).append(": ").append(field.get(employee)).append(", ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        sb.delete(sb.length() - 2, sb.length()); // удаление лишней запятой
        sb.append("}");
        return sb.toString();
    }
}
