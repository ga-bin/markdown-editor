package com.note.app.note.dto;

import com.note.app.note.entity.Note;
import com.note.app.note.entity.Tag;
import com.note.app.note.entity.Tag.TagBuilder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TagDTO {

	private int tagId;
	private String tagName;
	private NoteDTO noteDTO;
	
	public static TagDTOBuilder builder() {
		return new TagDTOBuilder();
	}

	public static class TagDTOBuilder {
		private int tagId;
		private String tagName;
		private NoteDTO noteDTO;
		
		
		TagDTOBuilder() {};
		
		public TagDTOBuilder tagId(int tagId) {
			this.tagId = tagId;
			return this;
		}
		
		public TagDTOBuilder tagName(String tagName) {
			this.tagName = tagName;
			return this;
		}
		
		public TagDTOBuilder noteDTO(NoteDTO noteDTO) {
			this.noteDTO = noteDTO;
			return this;
		}
		
		public TagDTO build() {
			return new TagDTO(tagId, tagName, noteDTO);
		}
	}
	
	public Tag toEntityWithoutNote() {
		TagBuilder builder = Tag.builder();
		
		builder.tagId(tagId)
			   .tagName(tagName);
		
		
		return builder.build();
	}
	

}
