package org.example.hw5spring.service;


import org.example.hw5spring.entity.Note;

import java.util.List;

public interface NoteService {

    public Note findById(Long id);

    public List<Note> findAll();

    public List<Note> findImportantNotes();

    public void saveNote(Note note);

    public void deleteById(Long id);

}
