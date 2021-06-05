package org.serratec.backend.service;

import org.serratec.backend.entity.EnderecoEntity;
import org.serratec.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public void  salvarEndereco(EnderecoEntity endereco) {
		
		enderecoRepository.save(endereco);
		
	}

}
