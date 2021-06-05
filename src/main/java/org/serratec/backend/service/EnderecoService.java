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
	
	public EnderecoEntity adicionandoDadosAoEndereco(EnderecoDTO cep) throws HasErrorInResponseCepException {
	
		ViaCepEntity cepValidado = serviceCep.pegarCep(cep.getCEP());
		EnderecoEntity endereco = new EnderecoEntity();
		endereco.setBairro(cepValidado.getBairro());
		endereco.setCEP(cepValidado.getCep());
		endereco.setCidade(cepValidado.getLocalidade());
		endereco.setEstado(cepValidado.getUf());
		endereco.setRua(cepValidado.getLogradouro());
		endereco.setComplemento(cep.getComplemento());
		endereco.setNumero(cep.getNumero());
		
		
		return endereco;
	}
	public EnderecoEntity saveInDataBase(EnderecoEntity endereco) {
		
		
		return adressRepository.save(endereco);
	}
	public String  updateadress(Long id, EnderecoDTO endereco) {
		
//		EnderecoEntity enderecoAtual = adressRepository.getById(id);
//		if(endereco.getBairro() != null) {			
//			enderecoAtual.setBairro(endereco.getBairro());
//		}
//		if(endereco.ge != null) {			
//			enderecoAtual.setBairro(endereco.getBairro());
//		}
//		
		
		return "Endereço atualizado com sucesso.";
	}

}
