package org.example.homeworksseminar5.repositories;


import org.example.homeworksseminar5.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
