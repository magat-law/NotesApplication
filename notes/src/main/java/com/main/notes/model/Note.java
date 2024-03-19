package com.main.notes.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Note {

	private int id;
	
	@NotEmpty(message = "Title is required")
	private String title;
	
	@NotEmpty(message = "Content is required")
	@Size(max = 3000, message = "Content should not exceed 3000 characters")
	private String content;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
