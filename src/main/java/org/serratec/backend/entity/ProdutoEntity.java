package org.serratec.backend.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
@Table(name = "produto")
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=5, max=100)
	private String nome;
	
	@NotNull
	@Size(min=5, max=100)
	private String descricao;
	
	@NotNull
	private Double preco;
	
	@NotNull
	private Integer qtdEstoque;
	

	private LocalDate dtCadastroProduto = LocalDate.now();
	
	public CategoriaEntity getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaEntity categoria) {
		this.categoria = categoria;
	}

	public void setProdutosPedidos(List<ProdutosPedidosEntity> produtosPedidos) {
		this.produtosPedidos = produtosPedidos;
	}

	private String imagem;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private CategoriaEntity categoria;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<ProdutosPedidosEntity> produtosPedidos; 
	

	public List<ProdutosPedidosEntity> getProdutosPedidos() {
		return produtosPedidos;
	}

	public void setProdutosPedidos(ProdutosPedidosEntity produtosPedidos) {
		this.produtosPedidos.add(produtosPedidos);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return "ProdutoEntity [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", preco=" + preco
				+ ", qtdEstoque=" + qtdEstoque + ", dtCadastroProduto=" + dtCadastroProduto + ", imagem=" + imagem
				+ ", categoria=" + categoria + ", produtosPedidos=" + produtosPedidos + "]";
	}
	
}
