package com.main.notes.api;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.notes.model.Note;
import com.main.notes.repository.NoteRepository;

@RestController
@RequestMapping("/API")
public class NoteAPI {

	@Autowired
	NoteRepository noteRepository;
	
	@GetMapping("/getAllNotes")
	public ResponseEntity<Set<Note>> getAllNotes() {
		try {
			Set<Note> notes = noteRepository.getAllNotes();
			if(notes.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(notes, HttpStatus.OK);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getNoteById/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable int id) {
		Note note = noteRepository.getNoteById(id);
		
		if(note == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(note, HttpStatus.OK);
		}
	}
	
	@PostMapping("/addNote")
	public ResponseEntity<Note> addNote(@RequestBody Note newNote) {
		if(ObjectUtils.isEmpty(newNote.getContent()) && ObjectUtils.isEmpty(newNote.getTitle())) {
			throw new IllegalArgumentException("Note title and/or content is empty");
		}
		
		noteRepository.addNote(newNote);
		return new ResponseEntity<>(newNote, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteNoteById/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable int id) {
		Note note = noteRepository.getNoteById(id);
		
		if(ObjectUtils.isEmpty(note)) {
			throw new NullPointerException("Unable to delete since no note was found!");
		}
		
		noteRepository.deleteNoteById(id);
		return new ResponseEntity<>(note, HttpStatus.OK);
	}
	
	@PutMapping("/updateNoteById/{id}")
	public ResponseEntity<Note> updateNoteById(@PathVariable int id, @RequestBody Note newNote) {
		Note note = noteRepository.getNoteById(id);
		
		if(ObjectUtils.isEmpty(note)) {
			throw new NullPointerException();
		}
		
		newNote.setId(id);
		noteRepository.updateNote(newNote);
		
		return new ResponseEntity<>(newNote, HttpStatus.OK);
	}
}
