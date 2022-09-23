package br.com.aceleragep.GabrielListaRestAPI.converts;

import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aceleragep.GabrielListaRestAPI.dto.inputs.ListaInput;
import br.com.aceleragep.GabrielListaRestAPI.dto.outputs.ListaOutput;
import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;


@Component
public class ListaConvert {
	
	@Autowired
	private ModelMapper model;

	public ListaOutput entityToOutput(ListaEntity listaEntity) {
		return model.map(listaEntity, ListaOutput.class);
	}

	public List<ListaOutput> ListEntityToOutput(List<ListaEntity> autoresEntity) {
		return autoresEntity.stream().map(autor -> this.entityToOutput(autor)).collect(Collectors.toList());
	}


	public ListaEntity inputToEntity(ListaInput listaInput) {
		return model.map(listaInput, ListaEntity.class);
	}

	public void copyInputToEntity(ListaInput listaInput, ListaEntity listaEntity) {
		model.map(listaInput, listaEntity);
	}
}

