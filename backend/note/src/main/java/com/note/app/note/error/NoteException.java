package com.note.app.note.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NoteException extends RuntimeException {

	private final NoteErrorResult noteErrorResult;
}
