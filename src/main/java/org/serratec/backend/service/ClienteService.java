package org.serratec.backend.service;

import java.util.List;

import org.serratec.backend.dto.ClienteDTO;
import org.serratec.backend.dto.LogarCliente;
import org.serratec.backend.entity.ClienteEntity;
import org.serratec.backend.entity.EnderecoEntity;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.mapper.ClienteMapper;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	//ClienteRepository clienteRepository = new ClienteRepository();

	@Autowired
	ClienteMapper clienteMapper;
	
	@Autowired
	EnderecoService enderecoService;

	// GetAll
//	public List<ClienteDTO> findAllCliente(String nome){
//		return clienteRepository.findAll(Sort.by(nome)).stream().map(clienteMapper::toDto).collect(Collectors.toList());
//	}
	public Boolean verificarId(Long id) {
		return clienteRepository.existsById(id);
	}

	// GET
	public List<ClienteEntity> findAll() {
		return clienteRepository.findAll();
	}

	public ClienteDTO findById(Long id) {
		if (verificarId(id)) {
			return clienteMapper.toDto(clienteRepository.findById(id).get());
		}
		return null; // ADICIONAR TRATAMENTO DE ERRO
	}

	// POST
	public ClienteDTO create(ClienteDTO dto) throws HasErrorInResponseCepException{
	 EnderecoEntity endereco = enderecoService.adicionandoDadosAoEndereco(dto.getEndereco());
	 ClienteEntity cliente = clienteMapper.toEntity(dto);
	 cliente.setEndereco(endereco);
	 endereco.setCliente(cliente);
	 enderecoService.saveInDataBase(endereco);
		return clienteMapper.toDto(clienteRepository.save(cliente));
	}

	// PUT
	public ClienteDTO update(Long id, ClienteDTO dto) {
		if (verificarId(id)) {
			ClienteEntity cliente = clienteRepository.getById(id);
			if (dto.getEmail() != null) {
				cliente.setEmail(dto.getEmail());
			}
			if (dto.getUsername() != null) {
				cliente.setUsername(dto.getUsername());
			}
			if (dto.getSenha() != null) {
				cliente.setSenha(dto.getSenha());
			}
			if (dto.getNome() != null) {
				cliente.setNome(dto.getNome());
			}

			if (dto.getTelefone() != null) {
				cliente.setTelefone(dto.getTelefone());
			}
			if (dto.getDtNascimento() != null) {
				cliente.setDtNascimento(dto.getDtNascimento());
			}

			// Endereco

			clienteRepository.saveAndFlush(cliente);
			return dto;
		}
		return null; // ADICIONAR TRATAMENTO DE ERRO
	}

	// DELETE
	public String delete(Long id) {
		if (verificarId(id)) {
			clienteRepository.deleteById(id);
			return "Cliente deletado com sucesso!";
		}
		return "Cliente não encontrado!";
	}

	public ClienteDTO logar(LogarCliente dto) {
		List <ClienteEntity> lista = findAll();
		for (ClienteEntity iterator : lista) {
			if(iterator.getEmail().equals(dto.getEmail())) {
				if(iterator.getSenha().equals(dto.getSenha())) {
					return clienteMapper.toDto(iterator);
				}
			}
		}
		
		
		return null;
	}

}
