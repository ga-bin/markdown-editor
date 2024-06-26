package com.note.app.note.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoteErrorResult {
	NOTE_NOT_FOUND(HttpStatus.BAD_REQUEST, "등록된 노트가 없습니다."),
	NOTE_CONTENT_EMPTY(HttpStatus.BAD_REQUEST, "노트 내용이 없습니다."),
	NOTE_TITLE_EMPTY(HttpStatus.BAD_REQUEST, "노트 제목이 없습니다.");
	
	private final HttpStatus status;
	private final String message;
}
