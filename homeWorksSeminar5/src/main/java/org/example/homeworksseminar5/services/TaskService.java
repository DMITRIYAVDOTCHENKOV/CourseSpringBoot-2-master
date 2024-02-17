package org.example.homeworksseminar5.services;


import lombok.AllArgsConstructor;
import org.example.homeworksseminar5.models.Status;
import org.example.homeworksseminar5.models.Task;
import org.example.homeworksseminar5.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис осуществляющий работу с задачами.
 */
@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    /**
     * Создание новой задачи.
     *
     * @param task объект задачи.
     * @return созданную задачу.
     */
    public Task addTask(Task task) {
        task.setStatus(Status.TO_DO);
        taskRepository.save(task);
        return task;
    }

    /**
     * Обновление задачи.
     *
     * @param task объект задачи.
     */
    public void updateTask(Task task) {
        updateStatus(task);
        taskRepository.save(task);
    }

    /**
     * Удаление задачи.
     *
     * @param id уникальный идентификатор задачи.
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Получение задачи.
     *
     * @param id уникальный идентификатор задачи.
     * @return объект задачи.
     */
    public Task getTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);
        if (task != null) {
            updateStatus(task);
        }
        return task;
    }

    /**
     * Получение всех задач.
     *
     * @return список задач.
     */
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    /**
     * Получение задач по заданному статусу.
     *
     * @param status статус задачи.
     * @return список задач с заданным статусом.
     */
    public List<Task> getTaskFormStatus(Status status) {
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(this::updateStatus);
        return tasks.stream().filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    /**
     * Смена статуса у задачи.
     *
     * @param id     уникальный идентификатор задачи.
     * @param status статус задачи.
     */
    public void changeStatus(Long id, Status status) {
        Task task = getTask(id);
        if (task != null){
            task.setStatus(status);
            taskRepository.save(task);
        }
    }

    /**
     * Служебный метод изменения статуса задачи.
     *
     * @param task объект задачи.
     */
    private void updateStatus(Task task) {
        // Если задача не выполнена
        if (!task.getStatus().equals(Status.COMPLETED)) {
            // Если задача просрочена
            if (LocalDateTime.now().isAfter(task.getLeadTime())) {
                task.setStatus(Status.OVERLAY);
                // Если до срока задачи менее часа
            } else if (LocalDateTime.now().plusHours(1).isAfter(task.getLeadTime())) {
                task.setStatus(Status.EXPIRE);
            } else {
                task.setStatus(Status.TO_DO);
            }
        }
    }
}
