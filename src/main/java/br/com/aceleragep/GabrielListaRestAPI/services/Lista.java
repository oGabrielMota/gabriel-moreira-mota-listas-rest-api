package br.com.aceleragep.GabrielListaRestAPI.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;
import br.com.aceleragep.GabrielListaRestAPI.exeptions.NotFoundBussinessException;
import br.com.aceleragep.GabrielListaRestAPI.repository.ListaRepository;

@Service
public class Lista {
	@Autowired
	private ListaRepository listaRepository;
	
	public ListaEntity cria(ListaEntity listaEntity) {
		return listaRepository.save(listaEntity);
	}
	
	public ListaEntity atualiza(ListaEntity listaEntity) {
		return listaRepository.save(listaEntity);
	}
	
	public ListaEntity buscaPeloIdLista(long id) {
		Optional<ListaEntity> encontrou = listaRepository.findById(id);
		if(encontrou.isPresent()) {
			return encontrou.get();
		} else {
			throw new NotFoundBussinessException("Lista " + id + " não encontrada");
		}
	}
	
	public Page<ListaEntity> listaTodos(Pageable paginacao){
		Page<ListaEntity> encontrou = listaRepository.findAll(paginacao);
		return encontrou;
	}
}
