package com.note.app.note.service;

import java.util.List;

import com.note.app.note.dto.NoteDTO;

public interface NoteService {

	public void insertNote(NoteDTO noteDTO);

	public void updateNote(NoteDTO noteDTO);
	
	public void deleteNote(int noteId);
	
	public NoteDTO getNote(int noteId);
	
	public List<NoteDTO> getNoteList(int cursor, int size, String keyword);

	public boolean hasNextPage(Integer lastNoteListId, String keyword);

	
}
