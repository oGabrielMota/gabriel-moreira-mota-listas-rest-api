package br.com.aceleragep.GabrielListaRestAPI.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aceleragep.GabrielListaRestAPI.entities.ItemEntity;

@Repository
public interface ItemRepository extends JpaRepository <ItemEntity, Long>{
	
	Page<ItemEntity> findAllByAutoresId(Long id, Pageable paginacao);
}
