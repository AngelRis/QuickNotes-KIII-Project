package com.example.quicknotes.services.impl;

import com.example.quicknotes.model.Note;
import com.example.quicknotes.model.User;
import com.example.quicknotes.repository.NoteRepository;
import com.example.quicknotes.repository.UserRepository;
import com.example.quicknotes.services.NoteService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Note saveNote(Note note,User user) {
        Note newNote = new Note();
        newNote.setTitle(note.getTitle());
        newNote.setContent(note.getContent());
        newNote.setUser(user);
        noteRepository.save(newNote);
        user.getNotes().add(newNote);
        return newNote;
    }

    @Override
    public void deleteNote(Long noteId,User user) {
        noteRepository.deleteById(noteId);
    }

    @Override
    public Note updateNote(Note noteUpdate,Long noteId) {
        Note note=noteRepository.findById(noteId).orElseThrow(()->new RuntimeException("Note not found!"));
        note.setTitle(noteUpdate.getTitle());
        note.setContent(noteUpdate.getContent());
        return noteRepository.save(note);

    }

    @Override
    public List<Note> getAllNotes(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()) {
            return noteRepository.findAllByUser(user.get());
        }else return new ArrayList<Note>();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(()->new RuntimeException("Note not found!"));
    }
}
