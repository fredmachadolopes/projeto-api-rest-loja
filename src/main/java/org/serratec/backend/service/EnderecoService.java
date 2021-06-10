package org.serratec.backend.service;

import java.util.List;

import org.serratec.backend.dto.EnderecoDTO;
import org.serratec.backend.dto.ViaCepDTO;
import org.serratec.backend.entity.ClienteEntity;
import org.serratec.backend.entity.EnderecoEntity;
import org.serratec.backend.exceptionProject.AddressNotFound;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.exceptionProject.NaoHaEnderecoComEsseIdentificador;
import org.serratec.backend.logado.LogarCliente;
import org.serratec.backend.mapper.EnderecoMapper;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.EnderecoRepository;
import org.serratec.backend.util.ServiceViaCep;
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
	
	@Autowired
	ClienteRepository clienteRepository;
	
	
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
	
	public String adicionarNoCliente(EnderecoDTO dto, Long trocar) throws HasErrorInResponseCepException {
		// Adicionar validacao por token e adicionar ao cliente
		EnderecoEntity endereco = enderecoMapper.dtoToEndereco(adicionandoDadosAoEndereco(dto));
		endereco.setCliente(clienteRepository.getById(trocar));
          saveInDataBase(endereco);
			return "Endereco salvo no cliente";
 
	}
	public String  updateAddress(EnderecoDTO endereco, String identificador) throws NaoHaEnderecoComEsseIdentificador {
		try {
			
			EnderecoEntity enderecoAtual= addressRepository.findByIdentificador(identificador);
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
		}catch(Exception erro) {
			throw new NaoHaEnderecoComEsseIdentificador("Endereco não localizado verifique seu codigo");
		}
	}
	// Esse metodo retorna para o controller uma lista de endereco para a consulta.
	public List<EnderecoDTO> listOfAddress(LogarCliente logado) throws AddressNotFound{
		ClienteEntity cliente = clienteService.clienteLogado(logado);
		if(cliente != null) {
			
			return enderecoMapper.listaEnderecotoDTO( addressRepository.findAllByIdCliente(cliente.getId()));
		}
		
	       throw new AddressNotFound("Não há endereço disponivel;");
	}
	
	public String updateSpecificAddress(EnderecoDTO endereco, String identificador) throws NaoHaEnderecoComEsseIdentificador {
		//Falta implementar o token
		return updateAddress(endereco, identificador);
	}
	
	public boolean deletarEndereco(EnderecoEntity endereco) {
		try {
			addressRepository.deleteById(endereco.getId());
			return true;
		}catch(IllegalArgumentException erro) {
			System.out.println("Jogar erro aqui");
			return false;
		}
		
	}
	public String deletarEnderecoEspecifico(String identificador) {
		// adicionar o verificador token
		deletarEndereco(addressRepository.findByIdentificador(identificador));
		return "Endereco deletado com sucesso";
	}
	
	public EnderecoDTO pegarEndereco(Long id) {
	
		
		return enderecoMapper.enderecoToDto(addressRepository.findById(id).get());
	}

}
