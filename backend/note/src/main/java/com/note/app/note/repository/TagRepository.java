package com.note.app.note.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.note.app.note.entity.Note;
import com.note.app.note.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer>{

	List<Tag> findByNote(Note note);

}
