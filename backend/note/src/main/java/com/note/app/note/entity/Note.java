package com.note.app.note.entity;

import java.util.ArrayList;
import java.util.List;

import com.note.app.note.dto.NoteDTO;
import com.note.app.note.dto.NoteDTO.NoteDTOBuilder;
import com.note.app.note.dto.TagDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int noteId;
	private String title;
	@Lob
	@Column(columnDefinition = "LONGTEXT")
	private String content;
	@OneToMany(mappedBy = "note", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tag> tagList;
	
	public static NoteBuilder builder() {
		return new NoteBuilder();
	}
	
	public static class NoteBuilder {
		private int noteId;
		private String title;
		private String content;
		private List<Tag> tagList;
		
		NoteBuilder() {};
		
		public NoteBuilder noteId(int noteId) {
			this.noteId = noteId;
			return this;
		}
		
		public NoteBuilder title(String title) {
			this.title = title;
			return this;
		}
		
		public NoteBuilder content(String content) {
			this.content = content;
			return this;
		}
		
		public NoteBuilder tagList(List<Tag> tagList) {
			this.tagList = tagList;
			return this;
		}
		
		public Note build() {
			return new Note(noteId, title, content, tagList);
		}
	}
	
	
	
	public NoteDTO toDTO() {
		NoteDTOBuilder builder = NoteDTO.builder();
		
		builder.noteId(noteId);
		
		if(title != null) {
			builder.title(title);
		}
		
		if(content != null) {
			builder.content(content);
		}
		
		if(tagList != null && tagList.size() > 0) {
			List<TagDTO> tagDTOList = new ArrayList<>();
			tagList.forEach((tag) -> {
				tag.setNote(this);
				tagDTOList.add(tag.toDTO()); 
			});
			builder.tagDTOList(tagDTOList);
		}
		
		return builder.build();
	}
	
	
}
