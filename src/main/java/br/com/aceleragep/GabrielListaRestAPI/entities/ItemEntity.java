package br.com.aceleragep.GabrielListaRestAPI.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, name = "titulo", nullable = false)
	private String titulo;

	@Column(name = "concluido")
	private Boolean concluido = false;
	
	@ManyToOne
	@JoinColumn(name = "lista_id", nullable = false)
	private ListaEntity lista;
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ListaEntity getLista() {
		return lista;
	}

	public void setLista(ListaEntity lista) {
		this.lista = lista;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}
	
}
