package com.ms.books.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ms.books.dtos.BookDto;
import com.ms.books.entities.Book;
import com.ms.books.services.BookService;

import io.swagger.annotations.Api;

@RestController
@Api(value = "API REST Books")
@CrossOrigin(origins = "*")
@RequestMapping("/book")
public class BookController {

	final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<Object> saveBook(@RequestBody @Valid BookDto bookDto) {

		if (bookService.existsByTitulo(bookDto.getTitulo())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Book is already save!");
		}
		var bookModel = new Book();
		BeanUtils.copyProperties(bookDto, bookModel); // conversão de bookDto para bookModel
		return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookModel));
	}

	@GetMapping
	public ResponseEntity<Page<Book>> getAllBooks(
			@PageableDefault(page = 0, size = 10, sort = "titulo", direction = Sort.Direction.ASC) Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll(pageable));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneBook(@PathVariable(value = "id") Long id) {
		Optional<Book> bookModelOptional = bookService.findById(id);
		if (!bookModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(bookModelOptional.get());

	}

	@GetMapping("/lido/{lido}")
	public ResponseEntity<List<Book>> getReadBook(@PathVariable(value = "lido") String lido) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.findByLido(lido));
	}

	@GetMapping("/emprestado/{emprestado}")
	public ResponseEntity<List<Book>> getBorrowedBook(@PathVariable(value = "emprestado") String emprestado) {
		return ResponseEntity.status(HttpStatus.OK).body(bookService.findByEmprestado(emprestado));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable(value = "id") Long id) {
		Optional<Book> bookModelOptional = bookService.findById(id);
		if (!bookModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
		}
		bookService.delete(bookModelOptional.get());
		return ResponseEntity.status(HttpStatus.OK).body("Book deleted!");

	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateBook(@PathVariable(value = "id") Long id, @RequestBody @Valid BookDto bookDto) {
		Optional<Book> bookModelOptional = bookService.findById(id);

		if (!bookModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
		}
		var bookModel = new Book();
		BeanUtils.copyProperties(bookDto, bookModel); // conversão de bookDto para bookModel
		bookModel.setId(bookModelOptional.get().getId());

		return ResponseEntity.status(HttpStatus.OK).body(bookService.save(bookModel));
	}

}
