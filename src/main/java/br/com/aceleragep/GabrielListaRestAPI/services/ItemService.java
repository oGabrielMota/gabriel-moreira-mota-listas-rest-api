package br.com.aceleragep.GabrielListaRestAPI.services;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aceleragep.GabrielListaRestAPI.entities.ItemEntity;
import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;
import br.com.aceleragep.GabrielListaRestAPI.exeptions.NotFoundBussinessException;
import br.com.aceleragep.GabrielListaRestAPI.repository.ItemRepository;


@Service
public class ItemService {
	
	@Autowired 
	private ItemRepository itemRepository;
	
	@Transactional
	public ItemEntity cria(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}
	
	public List<ItemEntity> listaTodos() {
		return itemRepository.findAll();
	}
	
	
	public ItemEntity buscaPeloId(Long id) {
		return itemRepository.findById(id).orElseThrow(() -> 
		new NotFoundBussinessException(String.format("O Item de id:%s não foi Encontrado", id)));
	}
	
	public List<ItemEntity> listaItensPelaLista(ListaEntity lista) {
		return itemRepository.findAllByLista(lista);
	}

	
	@Transactional
	public void deletaPeloId(Long id) {
		itemRepository.delete(this.buscaPeloId(id));
	}
	
	public ItemEntity altera(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}
	

	@Transactional
	public void marcarComoConcluido(ItemEntity itemEntity) {
		itemEntity.setConcluido(true);
		this.itemRepository.save(itemEntity);
	}
	
	@Transactional
	public void desmarcarConcluido(ItemEntity itemEntity) {
		itemEntity.setConcluido(false);
		this.itemRepository.save(itemEntity);
	}
	
	@Transactional
	public void deletaTudoLista(ListaEntity lista) {
		itemRepository.findAllByLista(lista).forEach(item ->{
			this.deletaPeloId(item.getId());
		});
	}
	
	public ItemEntity buscaItemPeloListaIdItemId(Long listaId, Long itemId) {
		return itemRepository.findBylistaIditemId(listaId, itemId).orElseThrow(()->
		new NotFoundBussinessException(String.format("O Item de id%s da Lista id%s não foi Encontrado", itemId, listaId)));	
	}
	
/// TESTEEEEE
	
	public ItemEntity buscaItemPeloListaIdItemIdTeste(Long itemId) {
		return itemRepository.findBylistaIditemIdTeste(itemId).orElseThrow(()->
		new NotFoundBussinessException(String.format("O Item de id%s não foi Encontrado", itemId)));	
	}

}
