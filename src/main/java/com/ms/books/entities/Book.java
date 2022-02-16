package com.ms.books.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "BOOK")
@Data
@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column(nullable = false, unique = true)
	private String titulo;
	@Column(nullable = false)
	private String autor;
	@Column(nullable = false)
	private String categoria;
	@Column(nullable = false, length = 1)
	private String lido;
	@Column(nullable = false, length = 1)
	private String emprestado;

}
