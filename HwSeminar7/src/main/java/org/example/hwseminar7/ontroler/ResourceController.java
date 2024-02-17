package org.example.hwseminar7.ontroler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @GetMapping("/private-data")
    public String privateData() {
        return "Это личные данные, доступные только для роли АДМИНИСТРАТОРА";
    }

    @GetMapping("/public-data")
    public String publicData() {
        return "Это общедоступные данные, доступные всем прошедшим проверку подлинности пользователям";
    }
}
