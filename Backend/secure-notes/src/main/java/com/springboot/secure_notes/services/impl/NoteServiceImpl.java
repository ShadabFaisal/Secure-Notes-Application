package com.springboot.secure_notes.services.impl;

import com.springboot.secure_notes.models.Note;
import com.springboot.secure_notes.repositories.NoteRepository;
import com.springboot.secure_notes.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String username, String content) {
        Note note=new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote=noteRepository.save(note);
        return savedNote;
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note=noteRepository.findById(noteId).orElseThrow(()-> new RuntimeException("Note not found!"));
        note.setContent(content);
        Note updatedNote=noteRepository.save(note);
        return updatedNote;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Note note=noteRepository.findById(noteId).orElseThrow(()-> new RuntimeException("Note not found!"));
        if(note!=null){
            noteRepository.deleteById(noteId);
        }
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        List<Note> persontalNotes=noteRepository.findByOwnerUsername(username);
        return persontalNotes;
    }
}
