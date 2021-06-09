package org.serratec.backend.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.serratec.backend.gerarIdentificador.GeradorDeIdentificacao;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoriaDTO {

	//@Autowired
//	GeradorDeIdentificacao geradorIdentificacao;
	@NotNull
	@Size(min = 5, max = 50)
	private String nome;

	@NotNull
	@Size(min = 5, max = 50)
	private String descricao;

	public String identificador ;

	//

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador =  identificador;
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
}
