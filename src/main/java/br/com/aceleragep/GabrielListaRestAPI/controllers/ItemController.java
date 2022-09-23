package br.com.aceleragep.GabrielListaRestAPI.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.aceleragep.GabrielListaRestAPI.config.URLConfig;
import br.com.aceleragep.GabrielListaRestAPI.converts.ItemConvert;
import br.com.aceleragep.GabrielListaRestAPI.dto.inputs.ItemInput;
import br.com.aceleragep.GabrielListaRestAPI.dto.outputs.ItemOutput;
import br.com.aceleragep.GabrielListaRestAPI.entities.ItemEntity;
import br.com.aceleragep.GabrielListaRestAPI.entities.ListaEntity;
import br.com.aceleragep.GabrielListaRestAPI.services.ItemService;
import br.com.aceleragep.GabrielListaRestAPI.services.ListaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Itens")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(URLConfig.URL_BASE + "/itens")
public class ItemController {

	@Autowired
	private ItemConvert itemConvert;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ListaService listaService;

	@Operation(summary = "Cria novo Item", description = "EndPoint destinado a Criar um novo item e vincular ele a uma Lista existe")
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ResponseEntity<ItemOutput> criaItem(@Valid @RequestBody ItemInput itemInput, UriComponentsBuilder uriBuild) {
		ListaEntity lista = listaService.buscaPeloIdLista(itemInput.getLista_id());
		ItemEntity itemNovo = itemConvert.inputToEntity(itemInput, lista);
		ItemEntity itemSalvo = itemService.cria(itemNovo);
		URI uri = uriBuild.path(URLConfig.URL_BASE + "/itens").buildAndExpand(itemSalvo.getId()).toUri();

		return ResponseEntity.created(uri).body(itemConvert.entityToOutput(itemSalvo));
	}

	@Operation(summary = "Altera item pelo ID" , description = "EndPoint destinado a Alterar um item já existente através do seu ID" )
	@PutMapping("/{id}")
	public ItemOutput alteraItem(@Parameter(description = "Id do Item", example = "1") @PathVariable Long id,
			@RequestBody @Valid ItemInput itemInput) {
		ItemEntity itemLocalizado = itemService.buscaPeloId(id);
		itemConvert.copyDataInputToEntity(itemInput, itemLocalizado);
		return itemConvert.entityToOutput(itemService.altera(itemLocalizado));
	}

	@Operation(summary = "Deleta item pelo ID", description = "EndPoint destinado a Excluir um item já existente através do seu ID" )
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void remove(@Parameter(description = "Id do Item", example = "1") @PathVariable Long id) {
		itemService.deletaPeloId(id);
	}

	@PutMapping("/{listaId}/marcar-item-concluido/{itenId}")
	@Operation(summary = "Marca Item da Lista como concluido" ,description = "EndPoint destinado a Marcar com Concluido um item que está vinculado a uma Lista, através do ID do item e ID da lista que ele está")
	public ItemOutput marcarItem(@Parameter(description = "Id da Lista", example = "1") @PathVariable Long listaId,
			@Parameter(description = "Id do Item", example = "1") @PathVariable Long itenId) {
		ItemEntity item = itemService.buscaItemPeloListaIdItemId(listaId, itenId);
		itemService.marcarComoConcluido(item);

		return itemConvert.entityToOutput(item);
	}

	@PutMapping("/{listaId}/desmarcar-item-concluido/{itenId}")
	@Operation(summary = "Desmarca Item concluido da Lista", description = "EndPoint destinado a Desmarcar com Concluido um item que está vinculado a uma Lista, através do ID do item e ID da lista que ele está")
	public ItemOutput desmarcarItem(@Parameter(description = "Id da Lista", example = "1") @PathVariable Long listaId,
			@Parameter(description = "Id do Item", example = "1") @PathVariable Long itenId) {
		ItemEntity item = itemService.buscaItemPeloListaIdItemId(listaId, itenId);
		itemService.desmarcarConcluido(item);

		return itemConvert.entityToOutput(item);
	}

	@Operation(summary = "Lista itens de uma lista por ID", description = "EndPoint destinado a listar Todos os Itens de uma Lista")
	@GetMapping("lista/{id}")
	public List<ItemOutput> ListaItens(@Parameter(description = "Id da Lista", example = "1") @PathVariable Long id) {
		ListaEntity lista = listaService.buscaPeloIdLista(id);
		List<ItemEntity> itens = itemService.listaItensPelaLista(lista);

		return itemConvert.listaEntityParaListaOutput(itens);
	}

}
