package org.serratec.backend.dto;

import javax.validation.constraints.NotNull;

public class EnderecoDTO {

	@NotNull
	private String cep;

	
	private String rua;


	private String bairro;


	private String cidade;

	@NotNull
	private String numero;


	private String complemento;

	private String estado;
	
	private String token;

	public void setToken(String token) {
		this.token = token;
	}

	public String getCEP() {
		return cep;
	}

	public void setCEP(String cep) {
		this.cep= cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "EnderecoDTO [cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", cidade=" + cidade + ", numero="
				+ numero + ", complemento=" + complemento + ", estado=" + estado + "]";
	}

	

}
