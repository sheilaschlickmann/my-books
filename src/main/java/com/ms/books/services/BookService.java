package com.ms.books.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ms.books.entities.Book;
import com.ms.books.repositories.BookRepository;

@Service
public class BookService {

	final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Transactional // caso der erro, faz rollback
	public Book save(Book bookModel) {

		return bookRepository.save(bookModel);
	}

	public boolean existsByTitulo(String titulo) {
		return bookRepository.existsByTitulo(titulo);
	}

	public Page<Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	public Optional<Book> findById(UUID id) {
		return bookRepository.findById(id);
	}

	@Transactional
	public void delete(Book book) {
		bookRepository.delete(book);

	}

}
