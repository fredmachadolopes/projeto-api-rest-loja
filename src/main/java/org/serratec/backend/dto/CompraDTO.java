package org.serratec.backend.dto;

public class CompraDTO {

	
	//Long idClienteTrocar, String produto,int quantidade, Long idLista
	
	@Override
	public String toString() {
		return "CompraDTO [idCliente=" + idCliente + ", produto=" + produto + ", quantidade=" + quantidade
				+ ", idLista=" + numeroPedido + "]";
	}

	private Long idCliente;
	
	private String produto;
	
	private Integer quantidade;
	
	private Long numeroPedido;
	
	private Double valorUnitario;
	
	private String codigoProduto;

	public String getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(String codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public Double getValor() {
		return valorUnitario;
	}

	public void setValor(Double valor) {
		this.valorUnitario = valor;
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

	public Long getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Long numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	
}
