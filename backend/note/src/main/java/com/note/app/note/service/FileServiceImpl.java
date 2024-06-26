package com.note.app.note.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.note.app.note.dto.NoteDTO;

@Service
public class FileServiceImpl implements FileService {

	private static final String FILE_PATH = "C:/binnote";
	
	public void saveMarkdownToFile(NoteDTO noteDTO) throws IOException {
		File directory = new File(FILE_PATH);
		if(!directory.exists()) {
			directory.mkdirs();
		}
		
		String fileName = noteDTO.getTitle().replaceAll(" ", "_") + ".md";
	
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(noteDTO.getContent());
		}
	}
}
