package com.main.notes.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.notes.model.Note;
import com.main.notes.repository.NoteRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/notes")
public class NoteController {

	@Autowired
	NoteRepository noteRepository;

	@GetMapping({"", "/"})
	public String showNotes(Model model) {
		//Populating all notes for testing purposes
		if(noteRepository.getAllNotes().isEmpty()) {
			noteRepository.populateNotes();
		}
		
		List<Note> notes = noteRepository.getAllNotes().stream().toList();

		model.addAttribute("notes", notes);
		return "notes/index";
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable int id) {
		Note note = noteRepository.getNoteById(id);
		
		if(note == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} 
		
		return new ResponseEntity<>(note, HttpStatus.OK);	
	}

	@GetMapping("/create")
	public String showCreateNotePage(Model model) {
		Note note = new Note();
		model.addAttribute(note);
		return "notes/CreateNote";
	}
	
	@PostMapping("/create")
	public String addNote(@Valid @ModelAttribute Note newNote, BindingResult result) {
		if(ObjectUtils.isEmpty(newNote.getContent()) && ObjectUtils.isEmpty(newNote.getTitle())) {
			result.addError(new FieldError("note", "Content/Title", "Content/Title should not be null"));
		}
		
		if(result.hasErrors()) {
			return "notes/CreateNote";
		}

		noteRepository.addNote(newNote);
		return "redirect:/notes";
	}
	
	@GetMapping("/update")
	public String showUpdateNotePage(Model model, @RequestParam int id) {
		Note note = noteRepository.getNoteById(id);
		
		if(ObjectUtils.isEmpty(note)) {
			System.err.print("No note found for this id!");
			return "redirect:/notes";
		}
		
		Note newNote = new Note();
		newNote.setId(id);
		newNote.setTitle(note.getTitle());
		newNote.setContent(note.getContent());
		
		model.addAttribute(newNote);
		return "notes/UpdateNote";
	}
	
	@PutMapping("/update")
	public String updateNote(Model model, @RequestParam int id, @RequestParam String title, @RequestParam String content, @Valid @ModelAttribute Note newNote, BindingResult result) {
		
		if(ObjectUtils.isEmpty(newNote.getContent()) && ObjectUtils.isEmpty(newNote.getTitle())) {
			result.addError(new FieldError("note", "Content/Title", "Content/Title should not be null"));
		}
		
		if(result.hasErrors()) {
			return "notes/UpdateNote";
		}

		noteRepository.updateNote(newNote);
		model.addAttribute(newNote);
		return "redirect:/notes";
	}
	
	@DeleteMapping("/delete")
	public String deleteNote(@RequestParam int id) {
		Note note = noteRepository.getNoteById(id);
		
		if(!ObjectUtils.isEmpty(note)) {
			noteRepository.deleteNoteById(id);
		} else {
			System.err.print("No note found for this id!");
		}
		
		return "redirect:/notes";
	}
}
