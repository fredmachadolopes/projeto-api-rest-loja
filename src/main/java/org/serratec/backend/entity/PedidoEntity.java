package org.serratec.backend.entity;

import java.time.LocalDate;
import java.util.ArrayList;
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

@Entity
@Table(name = "pedido")
public class PedidoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private static int numero = 1;
	@NotNull
	private Integer numeroPedido =  numero; //
	
	//private List<String>listaProdutoPedido;
	
	private Double valorTotalPedido = 0.0; //campo calculado
	
	private LocalDate dataPedido = LocalDate.now(); //data atual
	
	private LocalDate dataEntrega; //cálculo para a data
	
	private Boolean status = true; // ?
	
	@ManyToOne
	@JoinColumn(name="cliente_id", referencedColumnName = "id", nullable=false)
	private ClienteEntity cliente;
	
//	@OneToMany
//	@JoinColumn
//	private List<ProdutoEntity> produtos = new ArrayList<ProdutoEntity>(); 
	
	public PedidoEntity() {
		numero++;
	}
//	public List<ProdutoEntity> getProdutos() {
//		return produtos;
//	}
	
//	public void setProduto(ProdutoEntity produto) {
//		this.produtos.add(produto);
//	}
//
//	public void setProdutos(List<ProdutoEntity> produtos) {
//		this.produtos = produtos;
//	}


	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ProdutosPedidosEntity> produtosPedidos = new ArrayList<ProdutosPedidosEntity>(); 
	
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

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

//	public List<String> getListaProdutoPedido() {
//		return listaProdutoPedido;
//	}
//
//	public void setListaProdutoPedido(List<String> listaProdutoPedido) {
//		this.listaProdutoPedido = listaProdutoPedido;
//	}

	public Double getValorTotalPedido() {
		return valorTotalPedido;
	}

	public void setValorTotalPedido(Double valorTotalPedido) {
		this.valorTotalPedido = valorTotalPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	
	
	@Override
	public String toString() {
		return "PedidoEntity [id=" + id + ", numeroPedido=" + numeroPedido + ", valorTotalPedido=" + valorTotalPedido
				+ ", dataPedido=" + dataPedido + ", dataEntrega=" + dataEntrega + ", status=" + status + ", cliente="
				+ cliente + ", produtosPedidos=" + produtosPedidos + "]";
	}
}
