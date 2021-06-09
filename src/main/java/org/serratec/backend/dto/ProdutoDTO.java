package org.serratec.backend.dto;

import java.time.LocalDate;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class ProdutoDTO {

	@Null
	@Size(min = 5, max = 100)
	private String nome;

	@Null
	@Size(min = 5, max = 100)
	private String descricao;

	@Null
	private Double preco;

	@Null
	private Integer qtdEstoque;

	
	private LocalDate dtCadastroProduto;

	private String imagem;

	//

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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public LocalDate getDtCadastroProduto() {
		return dtCadastroProduto;
	}

	public void setDtCadastroProduto(LocalDate dtCadastroProduto) {
		this.dtCadastroProduto = dtCadastroProduto;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	@Override
	public String toString() {
		return "ProdutoDTO [nome=" + nome + ", descricao=" + descricao + ", preco=" + preco + ", qtdEstoque="
				+ qtdEstoque + ", dtCadastroProduto=" + dtCadastroProduto + ", imagem=" + imagem + "]";
	}
}
