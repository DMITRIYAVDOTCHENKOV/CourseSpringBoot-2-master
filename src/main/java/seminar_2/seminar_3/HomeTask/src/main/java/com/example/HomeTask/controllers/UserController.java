package com.example.HomeTask.controllers;

import com.example.HomeTask.domain.User;
import com.example.HomeTask.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")//localhost:8080/user
public class UserController {

    /**
     * Сервис регистрации пользователей.
     */
    @Autowired
    private RegistrationService service;
    /**
     * Получение списка пользователей.
     * @return JSON ответ со списком пользователей.
     */
    @GetMapping
    public List<User> userList() { return service.getDataProcessingService().getRepository().getUsers(); }

    /**
     * Добавление нового пользователя.
     * @param requestBody тело запроса.
     * @return подтверждение добавления пользователя.
     */
    @PostMapping("/body")
    public String userAddFromBody(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        Integer age = (Integer) requestBody.get("age");
        String email = (String) requestBody.get("email");
        List<User> userList = service.getDataProcessingService().getRepository().getUsers(); // Получаем список пользователей
        service.processRegistration(name, age, email, userList); // Передаем userList в процесс регистрации
        return "User added from body!";
    }

}