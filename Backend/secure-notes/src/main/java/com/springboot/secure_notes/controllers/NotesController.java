package com.springboot.secure_notes.controllers;

import com.springboot.secure_notes.models.Note;
import com.springboot.secure_notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public Note createNote(@RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails){
        String username=userDetails.getUsername();
        System.out.println("User Details: "+username);
        return noteService.createNoteForUser(username,content);
    }

    @GetMapping
    public List<Note> getUserNotes(@AuthenticationPrincipal UserDetails userDetails){
        String username=userDetails.getUsername();
        System.out.println("User Details: "+username);
        return noteService.getNotesForUser(username);
    }

    @PutMapping("/{noteId}")
    public Note updateNote(@PathVariable Long noteId, @RequestBody String content,
                           @AuthenticationPrincipal UserDetails userDetails){
        String username= userDetails.getUsername();
        return noteService.updateNoteForUser(noteId, content, username);
    }

    @DeleteMapping("/{noteId}")
    public void deleteNote(@PathVariable Long noteId,
                           @AuthenticationPrincipal UserDetails userDetails){
        String username= userDetails.getUsername();
        noteService.deleteNoteForUser(noteId,username);
    }




}
