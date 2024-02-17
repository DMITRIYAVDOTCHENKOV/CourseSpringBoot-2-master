package org.example.homeworksseminar5.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.example.homeworksseminar5.models.Status;

/**
 * Объект dto для задач.
 */
@Data
public class TaskDto {
    /**
     * Уникальный идентификатор задачи.
     */
    private Long id;
    /**
     * Заголовок задачи.
     */
    @Size(min = 2, max = 255, message = "Заголовок должен быть длиннее 2 символов и короче 255 символов!")
    private String title;
    /**
     * Описание задачи.
     */
    @NotEmpty(message = "Описание не может быть пустым!")
    private String description;
    /**
     * Время выполнения задачи.
     */
    @Pattern(regexp = "^\\d{2}:\\d{2}\\s\\d{2}-\\d{2}-\\d{4}$",
            message = "Дата должна быть в формате \"11:10 23-01-2024\"")
    private String leadTime;
    /**
     * Статус задачи.
     */
    private Status status;
}
