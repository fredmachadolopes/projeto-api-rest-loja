package org.serratec.backend.dto;

import java.time.LocalDate;
import java.util.List;

public class PedidoDTO {
	private Integer numeroPedido; //

	private List<String> listaProdutoPedido;

	private Double valorTotalPedido; // campo calculado

	private LocalDate dataPedido; // data atual

	private LocalDate dataEntrega; // cálculo para a data

	private Boolean status; // ?
	//

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public List<String> getListaProdutoPedido() {
		return listaProdutoPedido;
	}

	public void setListaProdutoPedido(List<String> listaProdutoPedido) {
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
