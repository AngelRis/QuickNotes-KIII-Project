package com.example.quicknotes.controller;
import com.example.quicknotes.model.Note;
import com.example.quicknotes.model.User;
import com.example.quicknotes.services.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public ResponseEntity<List<Note>> getNotes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return ResponseEntity.ok(noteService.getAllNotes(user.getUsername()));
    }

    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        noteService.saveNote(note,user);
        return ResponseEntity.ok(Map.of("message","Note created"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        noteService.deleteNote(id,user);
        return ResponseEntity.ok(Map.of("message","Note deleted"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@PathVariable Long id, @RequestBody Note note) {
        noteService.updateNote(note, id);
        return ResponseEntity.ok(Map.of("message","Note updated"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNote(@PathVariable Long id) {
        Note note = noteService.getNoteById(id);
        return ResponseEntity.ok(note);
    }
}