package com.note.app.util.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.note.app.note.error.NoteErrorResult;
import com.note.app.note.error.NoteException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({NoteException.class})
	public ResponseEntity<ErrorResponse> handlerNoteException(final NoteException exception) {
		log.info("noteException 발생 " + exception);
		NoteErrorResult errorResult = exception.getNoteErrorResult();
		ErrorResponse errorResponse = ErrorResponse.create(exception, errorResult.getStatus(), errorResult.getMessage());
		return ResponseEntity.status(errorResult.getStatus()).body(errorResponse);
	}
	
	
}
