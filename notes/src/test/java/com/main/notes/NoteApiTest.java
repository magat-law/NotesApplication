package com.main.notes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.main.notes.api.NoteAPI;
import com.main.notes.repository.NoteRepository;

@WebMvcTest(value = NoteAPI.class)
@RunWith(SpringRunner.class)
public class NoteApiTest {
	
	@MockBean
	@Autowired
	NoteRepository noteRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	NoteAPI noteApi;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAllNotes() throws Exception {
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/notes/getAllNotes").accept(MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		
//		String expectedResult = "";
//		
//		JSONAssert.assertEquals(expectedResult, result.getResponse()
//				.getContentAsString(), false);
	}

	@Test
	public void testGetNoteById() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddNote() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testDeleteNote() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testUpdateNoteById() {
		fail("Not yet implemented"); // TODO
	}

}
