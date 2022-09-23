package br.com.aceleragep.GabrielListaRestAPI.dto.inputs;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;


public class ItemInput {
	@NotEmpty(message = "Titulo é obrigatório!")
	@Length(min = 3, max = 100, message = "Titulo deve ter até 100 Caracteres")
	private String titulo;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Long getLista_id() {
		return lista_id;
	}
	public void setLista_id(Long lista_id) {
		this.lista_id = lista_id;
	}
	Long lista_id;
	
	
	
}
