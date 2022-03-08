package com.ms.books.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.books.controllers.BookController;
import com.ms.books.entities.Book;
import com.ms.books.services.BookService;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {

	@Autowired
	private BookController bookController;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean // vai criar uma imp falsa
	private BookService bookservice;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.bookController);
	}

	@Test
	public void bookTestGetAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/book")).andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void bookTestPost() throws Exception {
		Book bookModel = new Book(1L, "O cemitério", "Stephen King", "Terror", "S", "N");

		mockMvc.perform(MockMvcRequestBuilders.post("/book").contentType("application/json")
				.content(objectMapper.writeValueAsString(bookModel))).andExpect(MockMvcResultMatchers.status().isCreated());

	}
	

}
