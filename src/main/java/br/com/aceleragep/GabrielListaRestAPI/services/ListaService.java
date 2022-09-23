package br.com.aceleragep.GabrielListaRestAPI.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;
import br.com.aceleragep.GabrielListaRestAPI.exeptions.NotFoundBussinessException;
import br.com.aceleragep.GabrielListaRestAPI.repository.ListaRepository;

@Service
public class ListaService {
	
	@Autowired
	private ListaRepository listaRepository;
	
	@Transactional
	public ListaEntity cria(ListaEntity lista) {
		return listaRepository.save(lista);
	}
	
	public List<ListaEntity> listaTodos() {
		return listaRepository.findAll();
	}
	
	@Transactional
	public ListaEntity alterar(ListaEntity lista) {
		return listaRepository.save(lista);
	}
	

	public ListaEntity buscaPeloIdLista(long id) {
		return listaRepository.findById(id).orElseThrow(() -> 
		new NotFoundBussinessException(String.format("A lista de id:%s não foi Encontrado", id)));
	}
	
	@Transactional
	public void excluiPeloId(Long id) {
		listaRepository.delete(this.buscaPeloIdLista(id));
	}
}