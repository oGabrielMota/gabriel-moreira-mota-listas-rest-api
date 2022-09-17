package br.com.aceleragep.GabrielListaRestAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.aceleragep.GabrielListaRestAPI.services.Item;
import br.com.aceleragep.GabrielListaRestAPI.services.Lista;

public class ListaController {
	
	@Autowired
	private Lista listaService;
	
	@Autowired
	private Item itemService;
	
}
