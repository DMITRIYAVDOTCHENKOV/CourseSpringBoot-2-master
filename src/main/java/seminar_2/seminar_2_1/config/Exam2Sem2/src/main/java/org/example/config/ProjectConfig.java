package org.example.config;


import org.example.domain.Car;
import org.example.domain.Engin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


@Configuration
@ComponentScan("org.example.domain")
public class ProjectConfig {
    @Bean
    Car car(Engin eng)
    {
        Car obCar = new Car(eng);
        obCar.setModel("S8");
        obCar.setMade("Audi");
        return obCar;
    }

    @Bean
    Car car2(Engin eng)
    {
        Car obCar = new Car(eng);
        obCar.setModel("S8");
        obCar.setMade("Audi");
        return obCar;
    }


//    @Bean
//    Engin engin1()
//    {
//        Engin eng = new Engin();
//        eng.setTypeEngin("Бензиновый");
//        return eng;
//    }
//
//    @Bean
//    Engin engin2()
//    {
//        Engin eng = new Engin();
//        eng.setTypeEngin("Дизельный");
//        return eng;
//    }
//
//
//    @Bean
//    @Primary
//    org.example.domain.Car car(@Qualifier("engin2") Engin eng)
//    {
//        org.example.domain.Car obCar = new org.example.domain.Car(eng);
//        //obCar.setCarEngin(engin());
//        obCar.setModel("S8");
//        obCar.setMade("Audi");
//        return obCar;
//    }
//
//    @Bean
//    Car car2(@Qualifier("engin1") Engin eng)
//    {
//        Car obCar = new Car(eng);
//        //obCar.setCarEngin(engin());
//        obCar.setModel("S8");
//        obCar.setMade("Audi");
//        return obCar;
//    }
//

}
