package org.serratec.backend.service;

import org.serratec.backend.dto.EnderecoDTO;
import org.serratec.backend.entity.EnderecoEntity;
import org.serratec.backend.entity.ViaCepEntity;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
	
	@Autowired
	ServiceViaCep serviceCep;
	
	@Autowired
	EnderecoRepository adressRepository;
	
	public EnderecoDTO adicionandoDadosAoEndereco(EnderecoDTO cep) throws HasErrorInResponseCepException {
	
		ViaCepEntity cepValidado = serviceCep.pegarCep(cep.getCEP());
		cep.setBairro(cepValidado.getBairro());
		cep.setCEP(cepValidado.getCep());
		cep.setCidade(cepValidado.getLocalidade());
		cep.setEstado(cepValidado.getUf());
		cep.setRua(cepValidado.getLogradouro());
		cep.setComplemento(cep.getComplemento());
		cep.setNumero(cep.getNumero());
		
		
		return cep;
	}
	public EnderecoEntity saveInDataBase(EnderecoEntity endereco) {
		
		
		return adressRepository.save(endereco);
	}
	public String  updateAdress(EnderecoDTO endereco) {
		
		EnderecoEntity enderecoAtual= adressRepository.findEndereco(endereco.getRua(), endereco.getNumero(), endereco.getComplemento());
		if(endereco.getBairro() != null) {			
			enderecoAtual.setBairro(endereco.getBairro());
		}
		if(endereco.getRua() != null) {			
			enderecoAtual.setRua(endereco.getRua());
		}
		if(endereco.getNumero() != null) {			
			enderecoAtual.setNumero(endereco.getNumero());
		}
		if(endereco.getComplemento() != null) {			
			enderecoAtual.setComplemento(endereco.getComplemento());
		}
		if(endereco.getCidade() != null) {			
			enderecoAtual.setCidade(endereco.getCidade());
		}
		if(endereco.getEstado() != null) {			
			enderecoAtual.setEstado(endereco.getEstado());
		}
		if(endereco.getCEP() != null) {			
			enderecoAtual.setCEP(endereco.getCEP());
		}
		
		adressRepository.saveAndFlush(enderecoAtual);
		return "Endereço atualizado com sucesso.";
	}

}
