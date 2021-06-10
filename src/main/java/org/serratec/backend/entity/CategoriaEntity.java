package org.serratec.backend.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.backend.util.GeradorDeIdentificacao;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {

	public void setProduto(List<ProdutoEntity> produto) {
		this.produto = produto;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String nome;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String descricao;
	@NotNull
	public String identificador = new GeradorDeIdentificacao().retornaIdentificador();
	
	public boolean habilitado = true;
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	private List<ProdutoEntity> produto = new ArrayList<ProdutoEntity>();
	//
	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}


	public Long getId() {
		return Id;
	}

	public List<ProdutoEntity> getProduto() {
		return produto;
	}

	
	public void setProduto(ProdutoEntity produto) {
		this.produto.add(produto);
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
