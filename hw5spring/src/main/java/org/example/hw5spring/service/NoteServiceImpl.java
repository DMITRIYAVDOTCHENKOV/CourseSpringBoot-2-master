package org.example.hw5spring.service;


import org.example.hw5spring.entity.Note;
import org.example.hw5spring.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note findById(Long id){
        return noteRepository.getById(id);
    }

    public List<Note> findAll(){
        List<Note> result = noteRepository.findAll();
        Collections.sort(result);
        return result;
    }

    @Override
    public List<Note> findImportantNotes() {
        return noteRepository.getNoteByImportantTrue();
    }

    public void saveNote(Note note){
        noteRepository.save(note);
    }

    public void deleteById(Long id){
        noteRepository.deleteById(id);
    }
}
