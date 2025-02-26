package com.springboot.secure_notes.repositories;

import com.springboot.secure_notes.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByOwnerUsername(String ownerUsername);
}
