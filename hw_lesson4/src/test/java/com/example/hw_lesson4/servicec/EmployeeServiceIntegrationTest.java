package com.example.hw_lesson4.servicec;

import com.example.hw_lesson4.model.Employee;
import com.example.hw_lesson4.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    void testSaveEmployee() {
        // Подготовка данных
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setEmail("john.doe@example.com");

        // Делаем вызов сервиса для сохранения сотрудника
        Employee savedEmployee = employeeRepository.save(employee);

        // Проверка, что сотрудник сохранен
        assertEquals("John", savedEmployee.getFirstName());
        assertEquals("Doe", savedEmployee.getLastName());
        assertEquals("john.doe@example.com", savedEmployee.getEmail());
    }

}

