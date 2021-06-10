package org.serratec.backend.dto;

public class CompraDTO {

	
	//Long idClienteTrocar, String produto,int quantidade, Long idLista
	
	@Override
	public String toString() {
		return "CompraDTO [idCliente=" + idCliente + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", idLista=" + idPedido + "]";
	}

	private Long idCliente;
	
	private String produto;
	
	private Integer quantidade;
	
	private Long idPedido;
	
	private Double valor;

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}
	
}
