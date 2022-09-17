package br.com.aceleragep.GabrielListaRestAPI.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class ListaInput {
	@NotEmpty(message = "Titulo é obrigatório!")
	@Length(min = 3, max = 100, message = "Titulo deve ter até 100 Caracteres")
	private String titulo;

}
