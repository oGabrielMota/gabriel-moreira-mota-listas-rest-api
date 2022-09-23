package br.com.aceleragep.GabrielListaRestAPI.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;

@Repository
public interface ListaRepository extends JpaRepository<ListaEntity, Long>{
	
}
