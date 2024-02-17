package com.example.HomeTask.services;

import com.example.HomeTask.domain.User;
import com.example.HomeTask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {

    public UserRepository getRepository() {
        return repository;
    }

    @Autowired
    private UserRepository repository;


    public List<User> sortUsersByAge(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }

    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }

    public double calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }

    public void  addUserToList(User user)
    {
        repository.getUsers().add(user);
    }

    public void addUser(User newUser, List<User> userList) {
        try {
            // Добавляем нового пользователя в список
            userList.add(newUser);

            System.out.println("Пользователь " + newUser.getName() + " добавлен в систему.");

        } catch (Exception e) {
            // Обрабатываем возможные ошибки
            System.out.println("Ошибка при добавлении пользователя: " + e.getMessage());
        }
        System.out.println("Пользователь " + newUser.getName() + " добавлен в систему.");
        // Другие операции, например, сохранение информации о пользователе в базу данных, могут быть добавлены здесь
    }

    public String performOperations(User newUser) {
        // Пример реализации метода, выполняющего операции с новым пользователем
        // Для простоты примера, просто возвращаем "Success"
        return "Success";
        // Здесь могут выполняться другие операции, например, проверка данных, запуск процесса регистрации и другие операции, связанные с добавлением нового пользователя
    }
}