package org.serratec.backend.logado;

import java.time.LocalDateTime;

public class LogarCliente {

	
	private String email;
	private String senha;
	
	private String token;
	
	private LocalDateTime horaLogin;
	
	public LocalDateTime getHoraLogin() {
		return horaLogin;
	}
	public void setHoraLogin(LocalDateTime horaLogim) {
		this.horaLogin = horaLogim;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
