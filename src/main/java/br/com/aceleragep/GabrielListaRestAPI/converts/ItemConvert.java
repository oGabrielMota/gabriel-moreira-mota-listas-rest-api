package br.com.aceleragep.GabrielListaRestAPI.converts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.aceleragep.GabrielListaRestAPI.dto.inputs.ItemInput;
import br.com.aceleragep.GabrielListaRestAPI.dto.outputs.ItemOutput;
import br.com.aceleragep.GabrielListaRestAPI.entities.ItemEntity;
import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;
import br.com.aceleragep.GabrielListaRestAPI.services.Lista;

@Component
public class ItemConvert {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private Lista listaService;
	
	public ItemEntity inputToNewEntity(ItemInput itemInput) {
		ItemEntity itemEntity = modelMapper.map(itemInput, ItemEntity.class);
		converteIdsListaParaListas(itemInput, itemEntity);
		return itemEntity;
	}
	
	public void copyInputToEntity(ItemEntity itemEntity, ItemInput itemInput) {
		modelMapper.map(itemInput, itemEntity);
		converteIdsListaParaListas(itemInput, itemEntity);
	}
	
	
	public ItemOutput entityToOutput(ItemEntity itemEntity) {
		return modelMapper.map(itemEntity, ItemOutput.class);
	}
	
	public List<ItemOutput> entityToOutput(List<ItemEntity> items) {
		return items.stream().map(itemEntity -> {
			return entityToOutput(itemEntity);
		}).collect(Collectors.toList());
	}
	
	private void converteIdsListaParaListas(ItemInput itemInput, ItemEntity itemEntity) {
		List<ListaEntity> listas = new ArrayList<>();
		for (Long idLista : itemInput.getListas()) {
			ListaEntity lista = listaService.buscaPeloIdLista(idLista);
			listas.add(lista);
		}
//		itemEntity.setListas(listas);
		
	}
	
	public Page<ItemOutput> listPageEntityToListPageOutput(Page<ItemEntity> itemEncontrado){
		return itemEncontrado.map(this::entityToOutput);
	}
	
	
	
	
}
