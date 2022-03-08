package com.ms.books.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "BOOK")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
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
