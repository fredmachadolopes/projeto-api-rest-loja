package org.serratec.backend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.serratec.backend.util.GeradorDeIdentificacao;


@Entity
@Table(name = "endereco")
public class EnderecoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	// a única coisa que será digitada consumir informações do viaCep

	private String CEP;

	private String rua;
	
	private String identificador = new GeradorDeIdentificacao().retornaIdentificador();

	public String getToken() {
		return identificador;
	}

	public void setToken(String token) {
		this.identificador = token;
	}

	private String bairro;

	private String cidade;


	private String numero;


	private String complemento;

	@Column(name = "estado") //
	private String estado;
	
	// associação com a classe Cliente
	@ManyToOne(cascade= CascadeType.ALL) 
	@JoinColumn(name="cliente_id", referencedColumnName="id",nullable=false)
	private ClienteEntity cliente;

	//
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getCEP() {
		return CEP;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
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


	public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	
	
}
