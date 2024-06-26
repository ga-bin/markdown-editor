package com.note.app.note.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.note.app.note.dto.NoteDTO;
import com.note.app.note.service.NoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Note api", description = "Note api")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NoteController {

	private NoteService noteService;
	
	public NoteController(NoteService noteService) {
		this.noteService = noteService;
	}
	
	@Operation(summary = "note 등록")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "등록 성공")
	})
	@PostMapping("/insertNote")
	public ResponseEntity<Void> insertNote(@RequestBody NoteDTO noteDTO) {
		noteService.insertNote(noteDTO);
		return ResponseEntity.ok(null);
	}
	
	@Operation(summary = "note 수정")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "수정 성공")
	})
	@PutMapping("/updateNote")
	public ResponseEntity<Void> updateNote(@RequestBody NoteDTO noteDTO) {
		noteService.updateNote(noteDTO);
		return ResponseEntity.ok(null);
	}
	
	
	@Operation(summary = "note 삭제")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "삭제 성공")
	})
	@DeleteMapping("/deleteNote")
	public ResponseEntity<Void> deleteNote(@RequestParam int noteId) {
		noteService.deleteNote(noteId);
		return ResponseEntity.ok(null);
	}
	
	
	@Operation(summary = "note 목록")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "조회 성공", 
						content = @Content(schema = @Schema(implementation = Map.class)))
	})
	@GetMapping("/getAllNote")
	public ResponseEntity<Map<String, Object>> getNoteList(
		@Parameter(description = "커서(noteId)") @RequestParam(defaultValue = "0") int cursor,
		@Parameter(description = "한 페이지에 보여줄 개수 기본 10") @RequestParam(defaultValue = "10") int size,
		@Parameter(description = "검색어") @RequestParam(name = "keyWord", required = false) String keyword) {
	
		List<NoteDTO> noteDTOList = noteService.getNoteList(cursor, size, keyword);
		Integer lastNoteListId = noteDTOList.isEmpty() ? null : noteDTOList.get(noteDTOList.size() - 1).getNoteId();
		boolean hasNext = lastNoteListId != null && noteService.hasNextPage(lastNoteListId, keyword);
		
		Map<String, Object> response = new HashMap<>();
		response.put("noteList", noteDTOList);
		response.put("hasNext", hasNext);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@Operation(summary = "note 단일조회")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "조회 성공", 
						content = @Content(schema = @Schema(implementation = NoteDTO.class)))
	})
	@GetMapping("/getNote")
	public ResponseEntity<NoteDTO> getNote(@RequestParam int noteId) {
		NoteDTO noteDTO = noteService.getNote(noteId);
		return ResponseEntity.ok(noteDTO);
	}
	
}
