package br.com.aceleragep.GabrielListaRestAPI.converts;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.aceleragep.GabrielListaRestAPI.dto.inputs.ListaInput;
import br.com.aceleragep.GabrielListaRestAPI.dto.outputs.ListaOutput;
import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;


@Component
public class ListaConvert {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public ListaEntity inputToNewEntity(ListaInput listaInput) {
		return modelMapper.map(listaInput, ListaEntity.class);
	}
	
	public ListaOutput entityToOutput(ListaEntity listaEntity) {
		return modelMapper.map(listaEntity, ListaOutput.class);
	}
	
	public void copyInputToEntity(ListaEntity  listaEncontrada, @Valid ListaInput listaInput) {
		modelMapper.map(listaInput, listaEncontrada);
	}
	
	public Page<ListaOutput> entityToOutput(Page<ListaEntity> listas, Pageable paginacao) {
		return new PageImpl<>(listas.stream().map(autorEntity -> {
			return entityToOutput(autorEntity);
		}).collect(Collectors.toList()));
	}
	
	public Page<ListaOutput> listPageEntityToListPageOutput(Page<ListaEntity> item){
		return item.map(this::entityToOutput);
	}
	
}

