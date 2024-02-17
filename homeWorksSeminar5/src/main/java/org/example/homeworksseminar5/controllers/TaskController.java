package org.example.homeworksseminar5.controllers;

import lombok.AllArgsConstructor;
import org.example.homeworksseminar5.dto.DtoMapper;
import org.example.homeworksseminar5.dto.TaskDto;
import org.example.homeworksseminar5.models.Status;

import org.example.homeworksseminar5.models.Task;
import org.example.homeworksseminar5.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/")
public class TaskController {
    /**
     * Сервис для работы с объектами задач.
     */
    private final TaskService taskService;

    /**
     * Объект преобразующий dto в сущности и наоборот.
     */
    private final DtoMapper dtoMapper;

    /**
     * Эндпоинт домашней страницы приложения.
     *
     * @param model модель для передачи данных в представление.
     * @return домашнюю страницу.
     */
    @GetMapping
    public String home(Model model) {
        model.addAttribute("tasks_TO_DO",
                taskService.getTaskFormStatus(Status.TO_DO)
                        .stream().map(dtoMapper::toDto)
                        .collect(Collectors.toList()));
        model.addAttribute("tasks_EXPIRE",
                taskService.getTaskFormStatus(Status.EXPIRE)
                        .stream().map(dtoMapper::toDto)
                        .collect(Collectors.toList()));
        model.addAttribute("tasks_OVERLAY",
                taskService.getTaskFormStatus(Status.OVERLAY)
                        .stream().map(dtoMapper::toDto)
                        .collect(Collectors.toList()));
        return "home";
    }

    /**
     * Эндпоинт выполнения задачи.
     *
     * @param id идентификатор задачи.
     * @return перенаправление на домашнюю страницу.
     */
    @GetMapping("/tasks/complete/{id}")
    public String completeTask(@PathVariable("id") Long id) {
        taskService.changeStatus(id, Status.COMPLETED);
        return "redirect:/";
    }

    /**
     * Эндпоинт конкретной задачи.
     *
     * @param id    идентификатор задачи.
     * @param model модель для передачи данных в представление.
     * @return страницу конкретной задачи.
     */
    @GetMapping("/tasks/{id}")
    public String getTask(@PathVariable("id") Long id,
                          Model model) {
        model.addAttribute("task",
                dtoMapper.toDto(taskService.getTask(id)));
        return "task";
    }

    /**
     * Эндпоинт добавления новой задачи
     *
     * @param model модель для передачи данных в представление.
     * @return страницу создания новой задачи.
     */
    @GetMapping("/tasks/add")
    public String createTask(Model model) {
        model.addAttribute("new_task", new TaskDto());
        return "create_task";
    }

    /**
     * Эндпоинт формы представления добавления новой задачи.
     *
     * @param newTask       объект новой задачи с заполненными полями.
     * @param bindingResult объект хранящий результат валидации.
     * @return при успешной валидации перенаправление на домашнюю страницу,
     * иначе страница создания задачи с выявленными ошибками.
     */
    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute("new_task") @Validated TaskDto newTask,
                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "create_task";
        }
        taskService.addTask(dtoMapper.toEntity(newTask));
        return "redirect:/";
    }

    /**
     * Эндпоинт обновления существующей задачи.
     *
     * @param id    идентификатор задачи.
     * @param model модель для передачи данных в представление.
     * @return страницу для обновления задачи.
     */
    @GetMapping("/tasks/update/{id}")
    public String updateTask(@PathVariable("id") Long id,
                             Model model) {
        Task task = taskService.getTask(id);
        model.addAttribute("update_task",
                dtoMapper.toDto(task));
        return "update_task";
    }

    /**
     * Энпоинт формы представления обновления задачи.
     *
     * @param task          объект обновленной задачи с заполненными полями.
     * @param bindingResult объект хранящий результат валидации.
     * @return при успешной валидации перенаправление на домашнюю страницу,
     * иначе страница обновления задачи с выявленными ошибками.
     */
    @PostMapping("/tasks/update")
    public String formUpdate(@ModelAttribute("update_task") @Validated TaskDto task,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update_task";
        }
        taskService.updateTask(dtoMapper.toEntity(task));
        return "redirect:/";
    }

    /**
     * Эндпоинт удаления задачи.
     *
     * @param id идентификатор задачи.
     * @return перенаправление на домашнюю страницу.
     */
    @GetMapping("/tasks/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
