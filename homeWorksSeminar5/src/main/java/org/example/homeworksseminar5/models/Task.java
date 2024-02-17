package org.example.homeworksseminar5.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Сущность задачи.
 */
@Data
@Entity
@Table(name = "task_table")
public class Task {

    /**
     * Уникальный идентификатор задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Заголовок задачи.
     */
    private String title;
    /**
     * Описание задачи.
     */
    private String description;
    /**
     * Время выполнения задачи.
     */
    @Column(name = "lead_time")
    private LocalDateTime leadTime;
    /**
     * Статус задачи.
     */
    @Enumerated(EnumType.STRING)
    private Status status;

}
