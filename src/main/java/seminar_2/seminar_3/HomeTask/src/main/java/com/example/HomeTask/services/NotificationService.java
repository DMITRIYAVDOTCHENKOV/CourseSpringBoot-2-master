package com.example.HomeTask.services;

import com.example.HomeTask.domain.User;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }
        public void sendNotification(String message) {
            System.out.println("Уведомление: " + message);
        }

}
