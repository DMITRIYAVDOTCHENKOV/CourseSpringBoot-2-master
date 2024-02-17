package org.example.hw5spring.controller;


import jakarta.validation.Valid;

import org.example.hw5spring.entity.Note;
import org.example.hw5spring.service.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class NoteController {

    private final NoteServiceImpl noteServiceImpl;

    @Autowired
    public NoteController(NoteServiceImpl noteServiceImpl) {
        this.noteServiceImpl = noteServiceImpl;
    }

    @GetMapping("/*")
    public String root(){
        return "redirect:/notes";
    }

    @GetMapping("/notes")
    public String findAllNotes(Model model){
        List<Note> notes = noteServiceImpl.findAll();
        model.addAttribute("notes", notes);
        return "notes-list";
    }

    @GetMapping("/notes-important")
    public String findImportantNotes(Model model){
        List<Note> notes = noteServiceImpl.findImportantNotes();
        model.addAttribute("notes", notes);
        return "notes-list";
    }

    @GetMapping("/notes-create")
    public String createNotesForm(Note note, Model model){
        model.addAttribute("note", note);
        return "notes-create";
    }

    @PostMapping("/notes-create")
    public String createNote(@Valid Note note, Errors errors){
        if (errors.hasErrors()){
            return "notes-create";
        }
        note.setLastChange(LocalDateTime.now());
        note.setImportant(Boolean.FALSE);
        noteServiceImpl.saveNote(note);
        return "redirect:/notes";
    }

    @GetMapping("/notes-edit/{id}")
    public String editNoteForm( @PathVariable("id") Long id, Model model){
        Note note = noteServiceImpl.findById(id);
        model.addAttribute("note", note);
        return "notes-edit";
    }

    @PostMapping("/notes-edit")
    public String editNote(@Valid Note note, Errors errors){
        if (errors.hasErrors()){
            return "notes-edit";
        }
        note.setLastChange(LocalDateTime.now());
        noteServiceImpl.saveNote(note);
        return "redirect:/notes";
    }

    @GetMapping("/notes-toggle-important/{id}")
    public String toggleNoteImportant( @PathVariable("id") Long id){
        Note note = noteServiceImpl.findById(id);
        note.setImportant(!note.getImportant());
        noteServiceImpl.saveNote(note);
        return "redirect:/notes";
    }

    @GetMapping("/notes-del/{id}")
    public String deleteNote(@PathVariable("id") Long id){
        noteServiceImpl.deleteById(id);
        return "redirect:/notes";
    }
}