# NotesApplication
Notes application is a simple CRUD application for Note management

## Version 1.0

## Requirements
* Java 17
* Eclipse version 2024-03 (4.31.0) with STS version 4.22.0

## To run web notes application
1. Open project in Eclipse
2. Run Notes Application as Spring Boot App
3. Go to localhost:8080/
4. Click notes button

## Endpoints that can be used by POSTMAN
### /API/getAllNotes - GET MAPPING
Gets all notes
### /API/getNoteById/{id} - GET MAPPING
Gets a specific note by id
### /API/addNote - POST MAPPING
Creates a notes
Sample Request Body: 
{
    "title" : "test title 1",
    "content" : "test content 1"
}
### /API/deleteNoteById/{id} - DELETE MAPPING
Deletes a note by id
### /API/updateNoteById/{id} - PUT MAPPING
Updates a note by id

## Functionalities
* Show all Notes - Shows all notes that is stored
* Create Notes - Create a new note.
* Update Notes - Update an existing note
* Delete Notes - Delete a note

## Disclaimer
Notes data are currently processed as a Set list.
