package org.serratec.backend.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.backend.entity.ProdutoEntity;
import org.serratec.backend.util.GeradorDeIdentificacao;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoriaDTO {

	//@Autowired
//	GeradorDeIdentificacao geradorIdentificacao;
	@NotNull
	@Size(min = 5, max = 50)
	private String nome;

	@NotNull
	@Size(min = 5, max = 50)
	private String descricao;

	public String identificador ;

	private List<ProdutoDTO> produto = new ArrayList<ProdutoDTO>();
	//
	//

	public String getIdentificador() {
		return identificador;
	}

	public List<ProdutoDTO> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoDTO> produto) {
		this.produto = produto;
	}

	public void setIdentificador(String identificador) {
		this.identificador =  identificador;
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
