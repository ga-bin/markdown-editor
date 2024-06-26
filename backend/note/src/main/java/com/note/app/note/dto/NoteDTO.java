package com.note.app.note.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.note.app.note.entity.Note;
import com.note.app.note.entity.Note.NoteBuilder;
import com.note.app.note.entity.Tag;
import com.note.app.note.error.NoteErrorResult;
import com.note.app.note.error.NoteException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class NoteDTO {

	private int noteId;
	private String title;
	private String content;
	private List<TagDTO> tagDTOList;
	
	public static NoteDTOBuilder builder() {
		return new NoteDTOBuilder();
	}
	
	public static class NoteDTOBuilder {
		private int noteId;
		private String title;
		private String content;
		private List<TagDTO> tagDTOList;
		
		NoteDTOBuilder() {};
		
		public NoteDTOBuilder noteId(int noteId) {
			this.noteId = noteId;
			return this;
		}
		
		public NoteDTOBuilder title(String title) {
			this.title = title;
			return this;
		}
		public NoteDTOBuilder content(String content) {
			this.content = content;
			return this;
		}
		
		public NoteDTOBuilder tagDTOList(List<TagDTO> tagDTOList) {
			this.tagDTOList = tagDTOList;
			return this;
		}
		
		public NoteDTO build() {
			return new NoteDTO(noteId, title, content, tagDTOList);
		}
		
	}

	
	public Note toEntityWithoutTags() {
		NoteBuilder builder = Note.builder();
		
		builder.noteId(noteId);
		
		if(title != null) {
			builder.title(title);
		}
		
		if(content != null) {
			builder.content(content);
		}
		
		return builder.build();
				
	}


}
