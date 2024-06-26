package com.note.app.note.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.note.app.note.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Integer> {

	@Query("SELECT DISTINCT n FROM Note n LEFT JOIN n.tagList t " +
			"WHERE (n.noteId > :cursor) AND " +
			"(n.title LIKE %:keyword% OR n.content LIKE %:keyword% OR t.tagName LIKE %:keyword%) " +
			"ORDER BY n.noteId ASC")
	List<Note> findByKeywordWithCursor(@Param("cursor") int cursor, @Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT DISTINCT n FROM Note n LEFT JOIN n.tagList t " +
			"WHERE (n.noteId > :cursor) " +
			"ORDER BY n.noteId ASC"
		   )
	List<Note> findWithCursor(int cursor, Pageable pageable);

}
