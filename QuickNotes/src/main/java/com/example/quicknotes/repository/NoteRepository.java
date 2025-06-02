package com.example.quicknotes.repository;

import com.example.quicknotes.model.Note;
import com.example.quicknotes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUser(User user);
}
