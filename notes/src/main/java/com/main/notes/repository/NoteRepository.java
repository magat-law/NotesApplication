package com.main.notes.repository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import com.main.notes.model.*;

@Repository
public class NoteRepository {
	Note note;

	private Set<Note> notes = new HashSet<Note>();
	private int currentId = 0;
	
	public void populateNotes() {
		for(int index = 1 ; index <= 5 ; index++) {
			Note note = new Note();
			incrementId();
			note.setId(currentId);
			note.setTitle("Test Title " + index);
			note.setContent("Test Content " + index);
			notes.add(note);
		}
	}
	
	public int getCurrentId() {
		return currentId;
	}
	
	public void incrementId() {
		currentId++;
	}
	
	public Set<Note> getAllNotes() {
		return notes;
	}
	
	public Note getNoteById(int noteId) {		
		List<Note> filteredNote = notes.stream().filter(noteElement -> noteElement.getId() == noteId ).collect(Collectors.toList());
		
		return filteredNote.size() == 0 ? null : filteredNote.get(0);
	}
	
	public boolean addNote(Note note) {
		if(ObjectUtils.isEmpty(note.getId())  || note.getId() == 0) {
			note.setId(getCurrentId() + 1);
			incrementId();
		}
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
