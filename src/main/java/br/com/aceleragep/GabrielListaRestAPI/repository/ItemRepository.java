package br.com.aceleragep.GabrielListaRestAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.aceleragep.GabrielListaRestAPI.entities.ItemEntity;
import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
	List<ItemEntity> findAllByLista(ListaEntity lista);
	
	@Query("SELECT i FROM ItemEntity i WHERE i.id = :itenId AND lista.id = :listaId")
	Optional<ItemEntity> findBylistaIditemId(@Param("listaId") Long listaId, @Param("itenId") Long itenId);
	
	@Query("SELECT i FROM ItemEntity i WHERE i.id = :itenId")
	Optional<ItemEntity> findBylistaIditemIdTeste(@Param("itenId") Long itenId);
}
