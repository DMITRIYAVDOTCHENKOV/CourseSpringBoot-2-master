package com.example.hw_lesson4.controller;


import java.util.List;

import com.example.hw_lesson4.model.Employee;
import com.example.hw_lesson4.servicec.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Контроллер для управления сотрудниками
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Отображение домашней страницы со списком сотрудников
     *
     * @param model Модель для хранения данных
     * @return Страница со списком сотрудников
     */
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    /**
     * Отображение формы для добавления нового сотрудника
     *
     * @param model Модель для хранения данных
     * @return Страница с формой для добавления нового сотрудника
     */
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    /**
     * Сохранение информации о новом сотруднике
     *
     * @param employee Сущность сотрудника для сохранения
     * @return Перенаправление на домашнюю страницу
     */
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    /**
     * Отображение формы для обновления информации о сотруднике
     *
     * @param id    Идентификатор сотрудника
     * @param model Модель для хранения данных
     * @return Страница с формой для обновления информации о сотруднике
     */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    /**
     * Удаление информации о сотруднике
     *
     * @param id Идентификатор сотрудника
     * @return Перенаправление на домашнюю страницу
     */
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    /**
     * Поиск и отображение сотрудников с пагинацией
     *
     * @param pageNo    Номер страницы
     * @param sortField Поле для сортировки
     * @param sortDir   Направление сортировки
     * @param model     Модель для хранения данных
     * @return Страница со списком сотрудников
     */
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;
        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}
