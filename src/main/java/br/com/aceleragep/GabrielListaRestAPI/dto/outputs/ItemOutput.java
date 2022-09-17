package br.com.aceleragep.GabrielListaRestAPI.dto.outputs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemOutput {
	private Long id;
	private String titulo;
	private List<ListaOutput> listas;
}
