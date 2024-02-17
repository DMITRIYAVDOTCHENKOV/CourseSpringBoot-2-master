package org.example.hw5spring.repository;


import org.example.hw5spring.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    public List<Note> getNoteByImportantTrue();
}