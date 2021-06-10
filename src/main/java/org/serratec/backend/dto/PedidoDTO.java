package org.serratec.backend.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.entity.ProdutosPedidosEntity;

public class PedidoDTO {
	private Integer numeroPedido; //

	private List<ProdutosPedidosEntity> listaProdutoPedido;

	private Double valorTotalPedido; // campo calculado

	private LocalDate dataPedido; // data atual

	private LocalDate dataEntrega; // cálculo para a data

	private Boolean status; // ?
	
	private List<ProdutoDTO> produtos = new ArrayList<ProdutoDTO>(); 
	
	public List<ProdutoDTO> getProdutos() {
		return produtos;
	}
	
	public void setProduto(ProdutoDTO produto) {
		this.produtos.add(produto);
	}

	public void setProdutos(List<ProdutoDTO> produto) {
		this.produtos = produto;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public List<ProdutosPedidosEntity> getListaProdutoPedido() {
		return listaProdutoPedido;
	}

	public void setListaProdutoPedido(List<ProdutosPedidosEntity> listaProdutoPedido) {
		this.listaProdutoPedido = listaProdutoPedido;
	}

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
}
