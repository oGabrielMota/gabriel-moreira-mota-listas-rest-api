package br.com.aceleragep.GabrielListaRestAPI.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.aceleragep.GabrielListaRestAPI.config.URLConfig;
import br.com.aceleragep.GabrielListaRestAPI.converts.ListaConvert;
import br.com.aceleragep.GabrielListaRestAPI.dto.inputs.ListaInput;
import br.com.aceleragep.GabrielListaRestAPI.dto.outputs.ListaOutput;
import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;
import br.com.aceleragep.GabrielListaRestAPI.services.ItemService;
import br.com.aceleragep.GabrielListaRestAPI.services.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Listas")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(URLConfig.URL_BASE + "/listas")
public class ListaController {

	@Autowired
	private ListaService listaService;

	@Autowired
	private ListaConvert listaConvert;

	@Autowired
	private ItemService itemService;

	@Operation(summary = "Cria Lista", description = "EndPoint destinado a Criar uma nova Lista")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ListaOutput criaLista(@RequestBody @Valid ListaInput listaInput) {
		ListaEntity listaEntity = listaConvert.inputToEntity(listaInput);
		ListaEntity listaCriada = listaService.cria(listaEntity);

		return listaConvert.entityToOutput(listaCriada);
	}

	
	@Operation(summary = "Altera Lista", description = "EndPoint destinado a Alterar uma Lista já existente")
	@PutMapping("/{id}")
	public ListaOutput alteraLista(@Parameter(description = "Id da Lista", example = "1") @PathVariable Long id,
			@Valid @Parameter(description = "Representação de uma lista") @RequestBody ListaInput listaInput) {
		ListaEntity listaEntity = listaService.buscaPeloIdLista(id);
		listaConvert.copyInputToEntity(listaInput, listaEntity);
		return listaConvert.entityToOutput(listaService.alterar(listaEntity));
	}


	@Operation(summary = "Lista todas as listas", description = "EndPoint destinado a Listar todas as Lista já cadastradas")
	@GetMapping()
	public List<ListaOutput> listaTodos() {
		List<ListaEntity> livrosLocalizados = listaService.listaTodos();
		return listaConvert.ListEntityToOutput(livrosLocalizados);
	}


	@Operation(summary = "Busca por ID da lista",description = "EndPoint destinado a Buscar uma Lista já através do seu ID")
	@GetMapping("/{id}")
	public ListaOutput buscaListaPorId(@Parameter(description = "Id da Lista", example = "1") @PathVariable Long id) {
		ListaEntity listaEncontrada = listaService.buscaPeloIdLista(id);
		return listaConvert.entityToOutput(listaEncontrada);
	}


	@Operation(summary = "Deleta uma lista por ID",description = "EndPoint destinado a Remover uma Lista através do seu ID")
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remove(@Parameter(description = "Id da Lista", example = "1") @PathVariable Long id) {
		ListaEntity lista = listaService.buscaPeloIdLista(id);
		itemService.deletaTudoLista(lista);
		listaService.excluiPeloId(id);
	}
}
