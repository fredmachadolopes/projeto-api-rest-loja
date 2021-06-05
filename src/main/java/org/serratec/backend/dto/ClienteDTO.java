package org.serratec.backend.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

public class ClienteDTO {

	@NotNull
	@Email
	private String email;

	@Size(min = 5, max = 50) // verificar quantidade de caracteres
	@NotNull
	private String username;

	@Size(min = 5, max = 40) // deve usar MD5 no banco
	private String senha;

	@NotNull
	@Size(min = 5, max = 50) 
	private String nome;

	@NotNull
	@CPF
	private String cpf;

	@NotNull
	@Size(min = 8, max = 11)
	private String telefone;

	@Past
	@Column(name = "dtNascimento")
	private LocalDate dtNascimento;
	//

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cPF) {
		cpf = cPF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

}
