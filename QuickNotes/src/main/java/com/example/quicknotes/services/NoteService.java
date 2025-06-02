package com.example.quicknotes.services;

import com.example.quicknotes.model.Note;
import com.example.quicknotes.model.User;

import java.util.List;

public interface NoteService {
    Note saveNote(Note note, User user);
    void deleteNote(Long id,User user);
    Note updateNote(Note note,Long id);
    List<Note> getAllNotes(String username);
    Note getNoteById(Long id);
}
