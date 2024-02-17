package com.example.hw_lesson4.servicec;




import com.example.hw_lesson4.aspect.TrackUserAction;
import com.example.hw_lesson4.model.Employee;
import com.example.hw_lesson4.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Реализация сервиса для управления сотрудниками
 */
@Service

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired

    private EmployeeRepository employeeRepository;

    /**
     * Получение списка всех сотрудников
     *
     * @return Список всех сотрудников
     */
    @Override
    @TrackUserAction("Get all employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Сохранение информации о сотруднике
     *
     * @param employee Сущность сотрудника для сохранения
     */
    @Override
    @TrackUserAction("Save employee")
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    /**
     * Получение информации о сотруднике по идентификатору
     *
     * @param id Идентификатор сотрудника
     * @return Сущность сотрудника
     */
    @Override

    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee = null;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException("Employee not found for id :: " + id);
        }
        return employee;
    }

    /**
     * Удаление информации о сотруднике по идентификатору
     *
     * @param id Идентификатор сотрудника
     */
    @Override
    @TrackUserAction("Delete employee by id")
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }
    /**
     * Поиск сотрудников с пагинацией и сортировкой
     *
     * @param pageNo         Номер страницы
     * @param pageSize       Размер страницы
     * @param sortField      Поле для сортировки
     * @param sortDirection  Направление сортировки
     * @return Страница с отфильтрованным списком сотрудников
     */
    @Override

    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }
}

