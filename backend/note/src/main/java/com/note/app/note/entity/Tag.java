package com.note.app.note.entity;

import com.note.app.note.dto.TagDTO;
import com.note.app.note.dto.TagDTO.TagDTOBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tagId;
	private String tagName;
	@ManyToOne
	@JoinColumn(name="note_id")
	private Note note;

	public static TagBuilder builder() {
		return new TagBuilder();
	}
	

	public static class TagBuilder {
		private int tagId;
		private String tagName;
		private Note note;
		
		TagBuilder() {};
		
		public TagBuilder tagId(int tagId) {
			this.tagId = tagId;
			return this;
		}
		
		public TagBuilder tagName(String tagName) {
			this.tagName = tagName;
			return this;
		}
		
		public TagBuilder note(Note note) {
			this.note = note;
			return this;
		}
		
		public Tag build() {
			return new Tag(tagId, tagName, note);
		}
	}
	
	public TagDTO toDTO() {
		TagDTOBuilder builder = TagDTO.builder();
		builder.tagId(tagId)
			   .tagName(tagName);
		

		return builder.build();
	}
	
}
