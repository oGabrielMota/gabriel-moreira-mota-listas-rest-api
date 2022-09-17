package br.com.aceleragep.GabrielListaRestAPI.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "item")
public class ItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="titulo", length=100)
	private String titulo;
	
	@Column(name="concluido")
	private boolean concluido;
	
	@Column(name="lista")
	private List<ListaEntity> listas;
	
}
