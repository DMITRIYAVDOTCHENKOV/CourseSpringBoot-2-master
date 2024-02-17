package com.example.HomeTask.services;

import com.example.HomeTask.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {


    public DataProcessingService getDataProcessingService() {
        return dataProcessingService;
    }

    public UserService getUserService() {
        return userService;
    }

    public NotificationService getNotificationService() {
        return notificationService;
    }

    @Autowired
    private DataProcessingService dataProcessingService;
    @Autowired
    private UserService userService;
    @Autowired
    private NotificationService notificationService;

    public void processRegistration(String name, int age, String email, List<User> userList) {
        // Создание пользователя с помощью UserService
        User newUser = userService.createUser(name, age, email);

        // Добавление пользователя в список и выполнение операций над этим пользователем с использованием DataProcessingService
        dataProcessingService.addUser(newUser, userList); // передаем userList в метод addUser
        String operationResult = dataProcessingService.performOperations(newUser);

        // Использование NotificationService для вывода информации о выполненной операции
        notificationService.sendNotification("Регистрация пользователя " + newUser.getName() + " завершена. Результат операции: " + operationResult);
    }
}