package seminar_2.seminar_2_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import seminar_2.seminar_2_1.config.domain.Car;

@Configuration
public class ProjectConfig {

    @Bean("BMW")
    Car car1() {
        Car obCar = new Car();
        obCar.setModel("X1");
        obCar.setMade("BMW");
        return  obCar;
    }
    @Bean(name = "Honda")
    Car car2() {
        Car obCar = new Car();
        obCar.setModel("Civic");
        obCar.setMade("Honda");
        return  obCar;
    }

    @Bean
    @Primary
    Car car3() {
        Car obCar = new Car();
        obCar.setModel("H7");
        obCar.setMade("HAVAL");
        return  obCar;
    }

    @Bean
    String hello() {
        return "Hello";
    }
    @Bean
    Integer ten() {
        return 10;
    }
}
