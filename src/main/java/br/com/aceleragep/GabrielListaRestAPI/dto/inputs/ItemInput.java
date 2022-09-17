package br.com.aceleragep.GabrielListaRestAPI.dto.inputs;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

public class ItemInput {
	@NotEmpty(message = "Titulo é obrigatório!")
	@Length(min = 3, max = 100, message = "Titulo deve ter até 100 Caracteres")
	private String titulo;
	
	private boolean concluido;
	
	@NotEmpty(message = "Deve se vincular a pelo menos 1 lista!")
	private List<Long> Listas;
	
	public List<Long> getListas() {
		return Listas;
	}
	
}
