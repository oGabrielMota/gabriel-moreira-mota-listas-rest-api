package br.com.aceleragep.GabrielListaRestAPI.dto.inputs;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;


public class ListaInput {
	
	@NotBlank(message = "Titulo é obrigatório")
	@Length(message = "O campo Titulo deve ter no máximo 100 caracteres!", max = 100)
	String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
