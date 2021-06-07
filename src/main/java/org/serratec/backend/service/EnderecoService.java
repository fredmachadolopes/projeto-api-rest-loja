package org.serratec.backend.service;

import java.util.List;

import org.serratec.backend.dto.EnderecoDTO;
import org.serratec.backend.dto.ViaCepDTO;
import org.serratec.backend.entity.ClienteEntity;
import org.serratec.backend.entity.EnderecoEntity;
import org.serratec.backend.exceptionProject.AddressNotFound;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.logado.LogarCliente;
import org.serratec.backend.mapper.EnderecoMapper;
import org.serratec.backend.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
	
	@Autowired
	ServiceViaCep serviceCep;
	
	@Autowired
	EnderecoRepository addressRepository;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EnderecoMapper enderecoMapper;
	
	
	public EnderecoDTO adicionandoDadosAoEndereco(EnderecoDTO cep) throws HasErrorInResponseCepException {
	
		ViaCepDTO cepValidado = serviceCep.pegarCep(cep.getCEP());
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
		
		
		return addressRepository.save(endereco);
	}
	public String  updateAddress(EnderecoDTO endereco) {
		
		EnderecoEntity enderecoAtual= addressRepository.findEndereco(endereco.getRua(), endereco.getNumero(), endereco.getComplemento());
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
		
		addressRepository.saveAndFlush(enderecoAtual);
		return "Endereço atualizado com sucesso.";
	}
	// Esse metodo retorna para o controller uma lista de endereco para a consulta.
	public List<EnderecoDTO> listOfAddress(LogarCliente logado) throws AddressNotFound{
		ClienteEntity cliente = clienteService.clienteLogado(logado);
		if(cliente != null) {
			
			return enderecoMapper.listaEnderecotoDTO( addressRepository.findAllByIdCliente(cliente.getId()));
		}
		
	       throw new AddressNotFound("Não há endereço disponivel;");
	}
	
	public String updateSpecificAddress(EnderecoDTO endereco, String token) {
		//Falta implementar o token
		return updateAddress(endereco);
	}
	
	public void deletarEndereco(EnderecoEntity endereco) {
		addressRepository.deleteById(endereco.getId());
	}

}
