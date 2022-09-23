package br.com.aceleragep.GabrielListaRestAPI.converts;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.aceleragep.GabrielListaRestAPI.dto.inputs.ItemInput;
import br.com.aceleragep.GabrielListaRestAPI.dto.inputs.ListaInput;
import br.com.aceleragep.GabrielListaRestAPI.dto.outputs.ItemOutput;
import br.com.aceleragep.GabrielListaRestAPI.entities.ItemEntity;
import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;


@Component
public class ItemConvert {
	
	@Autowired
	private ModelMapper model;

	public ItemOutput entityToOutput(ItemEntity intemEntity) {
		return model.map(intemEntity, ItemOutput.class);
	}
	
	public ItemEntity inputToEntity(ItemInput itemInput, ListaEntity lista) {
		ItemEntity itemEntity = model.map(itemInput, ItemEntity.class);
		itemEntity.setLista(lista);
		return itemEntity;
	}
	
	public ListaEntity inputToEntityAntigo(ListaInput listaInput) {
		return model.map(listaInput, ListaEntity.class);
	}
	
	
	public List<ItemOutput> listaEntityParaListaOutput(List<ItemEntity> listaTodos) {
		List<ItemOutput> itens = listaTodos.stream().map(this::entityToOutput).collect(Collectors.toList());
		return itens;
	}
	
	public void copyDataInputToEntity(ItemInput itemInput, ItemEntity itemEntity) {
		model.map(itemInput, itemEntity);
	}

	
	
}
