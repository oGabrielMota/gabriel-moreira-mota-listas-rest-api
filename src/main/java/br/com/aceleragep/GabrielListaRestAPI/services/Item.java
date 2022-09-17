package br.com.aceleragep.GabrielListaRestAPI.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aceleragep.GabrielListaRestAPI.entities.ItemEntity;
import br.com.aceleragep.GabrielListaRestAPI.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class Item {
	
	@Autowired
	private ItemRepository itemRepository;
	
	public ItemEntity cria(ItemEntity itemEntity) {
		return itemRepository.save(itemEntity);
	}
	
	public Page<ItemEntity> buscaItemPeloIdLista(long id, Pageable paginacao){
		return itemRepository.findAllByAutoresId(id, paginacao);
	}
	
	public void remove(ItemEntity livroLocalizado) {
		itemRepository.delete(livroLocalizado);
	}
	
	public ItemEntity altera(ItemEntity itemEntit) {
		return itemRepository.save(itemEntit);
	}
}
