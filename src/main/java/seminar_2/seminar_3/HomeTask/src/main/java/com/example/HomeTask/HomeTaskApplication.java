package com.example.HomeTask;

import com.example.HomeTask.services.DataProcessingService;
import com.example.HomeTask.services.NotificationService;
import com.example.HomeTask.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Домашнее задание:
//Создать сервис "RegistrationService", который принимает на вход данные о пользователе (имя, возраст, email),
// создает пользователя с помощью UserService, затем использует DataProcessingService для добавления пользователя в
// список и выполнения операций над этим списком. После выполнения каждой операции, использовать NotificationService
// для вывода информации о выполненной операции.

@SpringBootApplication
public class HomeTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeTaskApplication.class, args);
	}

}
