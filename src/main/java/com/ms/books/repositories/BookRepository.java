package com.ms.books.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ms.books.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {

	boolean existsByTitulo(String titulo);

	List<Book> findByLido(String lido);

	List<Book> findByEmprestado(String emprestado);

}
