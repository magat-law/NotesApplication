package com.main.notes.repository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.main.notes.model.*;

@Repository
public class NoteRepository {
	Note note;

	private Set<Note> notes = new HashSet<Note>();
	
	public void populateNotes() {
		for(int index = 1 ; index <= 5 ; index++) {
			Note note = new Note();
			note.setId(index);
			note.setTitle("Test Title " + index);
			note.setContent("Test Content " + index);
			notes.add(note);
		}
	}
	
	public Set<Note> getAllNotes() {
		return notes;
	}
	
	public Note getNoteById(int noteId) {		
		List<Note> filteredNote = notes.stream().filter(noteElement -> noteElement.getId() == noteId ).collect(Collectors.toList());
		
		return filteredNote.size() == 0 ? null : filteredNote.get(0);
	}
	
	public boolean addNote(Note note) {
		return notes.add(note);
	}
	
	public boolean updateNote(Note note) {
		deleteNoteById(note.getId());
		return addNote(note);
	}
	
	public boolean deleteNoteById(int noteId) {
		return notes.remove(getNoteById(noteId));
	}
}
