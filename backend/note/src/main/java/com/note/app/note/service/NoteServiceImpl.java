package com.note.app.note.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.note.app.note.dto.NoteDTO;
import com.note.app.note.dto.TagDTO;
import com.note.app.note.entity.Note;
import com.note.app.note.entity.Tag;
import com.note.app.note.error.NoteErrorResult;
import com.note.app.note.error.NoteException;
import com.note.app.note.repository.NoteRepository;
import com.note.app.note.repository.TagRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class NoteServiceImpl implements NoteService {

	private NoteRepository noteRepository;
	private TagRepository tagRepository;
	
    @PersistenceContext
    private EntityManager entityManager;

    
	public NoteServiceImpl(NoteRepository noteRepository, TagRepository tagRepository) {
		this.noteRepository = noteRepository;
		this.tagRepository = tagRepository;
	}
	
	@Override
	@Transactional
	public void insertNote(NoteDTO noteDTO) {
		Note note = noteDTO.toEntityWithoutTags();
		noteRepository.save(note);
		List<TagDTO> tagDTOList = noteDTO.getTagDTOList();
		List<Tag> tagList = new ArrayList<>();
		tagDTOList.forEach((tagDTO) -> {
			Tag tag = tagDTO.toEntityWithoutNote();
			tag.setNote(note);
			tagList.add(tag);
		});
		
		tagRepository.saveAll(tagList);
	}
	

	@Override
	@Transactional
	public void updateNote(NoteDTO noteDTO) {
		Optional<Note> optionalNote = noteRepository.findById(noteDTO.getNoteId());
		Note note = null;
		
		if(optionalNote.isPresent()) {
			note = optionalNote.get();
		} else {
			throw new NoteException(NoteErrorResult.NOTE_NOT_FOUND);			
		}
		
		if(Objects.nonNull(noteDTO.getTitle())) {
			note.setTitle(noteDTO.getTitle());
		} else {
			throw new NoteException(NoteErrorResult.NOTE_TITLE_EMPTY);	
		}
		
		if(Objects.nonNull(noteDTO.getContent())) {
			note.setContent(noteDTO.getContent());
		} else {
			throw new NoteException(NoteErrorResult.NOTE_CONTENT_EMPTY);	
		}
		
		Note savedNote = noteRepository.save(note);
		
		
		if(Objects.nonNull(noteDTO.getTagDTOList()) && noteDTO.getTagDTOList().size() > 0) {
			List<Tag> savedTags = tagRepository.findByNote(savedNote);
			
			tagRepository.deleteAll(savedTags);
			entityManager.flush();
			
			List<Tag> tagList = new ArrayList();
			List<TagDTO> tagDTOList = noteDTO.getTagDTOList();
			tagDTOList.forEach((tagDTO) -> { 
				Tag tag = tagDTO.toEntityWithoutNote();
				tag.setNote(optionalNote.get());
				tagList.add(tag);
			});
			tagRepository.saveAll(tagList);
		}
	}
	
	@Override
	public void deleteNote(int noteId) {
		noteRepository.deleteById(noteId);
	}
	
	@Override
	public NoteDTO getNote(int noteId) {
		Optional<Note> optionalNote = noteRepository.findById(noteId); 
		if(optionalNote.isPresent()) {
			Note note = optionalNote.get();
			return note.toDTO();
		} else {
			throw new NoteException(NoteErrorResult.NOTE_NOT_FOUND);
		}
	}
	

	@Override
	public List<NoteDTO> getNoteList(int cursor, int size, String keyword) {
		Pageable pageable = PageRequest.of(0, size);
		List<Note> noteList = null;
		
		System.out.println(Objects.isNull(keyword));
		if(Objects.isNull(keyword) || "".equals(keyword)) {
			noteList = noteRepository.findWithCursor(cursor, pageable);
		} else {
			noteList = noteRepository.findByKeywordWithCursor(cursor, keyword, pageable);			
		}
		
		List<NoteDTO> noteDTOList = new ArrayList<>();
		noteList.forEach((Note) -> { noteDTOList.add(Note.toDTO());});
		return noteDTOList;
	}
	

	@Override
	public boolean hasNextPage(Integer lastNoteListId, String keyword) {
		Pageable pageable = PageRequest.of(0, 1);
		List<Note> nextNote = null;
		if(Objects.isNull(keyword) || "".equals(keyword)) {
			nextNote = noteRepository.findWithCursor(lastNoteListId, pageable);
		} else {
			nextNote = noteRepository.findByKeywordWithCursor(lastNoteListId, keyword, pageable);			
		}
		return !nextNote.isEmpty();
	}
	
}
 