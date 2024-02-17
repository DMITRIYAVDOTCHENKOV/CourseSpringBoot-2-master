package com.example.hw_lesson4.servicec;

import com.example.hw_lesson4.model.Employee;
import com.example.hw_lesson4.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getAllEmployees() {
        // Подготовка данных
        List<Employee> employees = List.of(new Employee());

        // Дано
        given(employeeRepository.findAll()).willReturn(employees);

        // Выполнение тестируемого метода
        List<Employee> result = employeeService.getAllEmployees();

        // Утверждение
        assertEquals(employees.size(), result.size());

    }

    @Test
    void saveEmployee() {
        // Подготовка данных
        Employee employee = new Employee();
        // Установка параметров сотрудника

        // Дано
        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        // Выполнение тестируемого метода
        employeeService.saveEmployee(employee);


    }

    @Test
    void getEmployeeById() {
        // Подготовка данных
        long id = 1;
        Employee employee = new Employee();
        employee.setId(id);
        // Установка параметров сотрудника

        // Дано
        given(employeeRepository.findById(id)).willReturn(Optional.of(employee));

        // Выполнение тестируемого метода
        Employee result = employeeService.getEmployeeById(id);

        // Утверждение
        assertEquals(id, result.getId());

    }

    @Test
    void deleteEmployeeById() {
        // Подготовка данных
        long id = 1;

        // Выполнение тестируемого метода
        employeeService.deleteEmployeeById(id);


    }

    @Test
    void findPaginated() {
        // Подготовка данных
        int pageNo = 1;
        int pageSize = 10;
        String sortField = "lastName";
        String sortDirection = "ASC";
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());

        // Дано
        given(employeeRepository.findAll((Example<Employee>) any())).willReturn((List<Employee>) new PageImpl<Employee>(employees));

        // Выполнение тестируемого метода
        Page<Employee> result = employeeService.findPaginated(pageNo, pageSize, sortField, sortDirection);

        // Утверждение
        assertEquals(employees.size(), result.getContent().size());
        // Другие утверждения по необходимости
    }
}
