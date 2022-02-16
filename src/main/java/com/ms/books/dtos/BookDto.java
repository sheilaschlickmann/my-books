package com.ms.books.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BookDto {

	@NotBlank(message = "Título deve ser informado")
	private String titulo;

	@NotBlank(message = "Autor deve ser informado")
	private String autor;

	@NotBlank(message = "Categoria deve ser informado")
	private String categoria;

	@NotBlank(message = "Lido deve ser informado")
	@Size(max = 1, message = "Este campo só pode conter no máximo um caractere 'S/N'")
	private String lido;

	@NotBlank(message = "Emprestado deve ser informado")
	@Size(max = 1, message = "Este campo só pode conter no máximo um caractere 'S/N'")
	private String emprestado;

}
