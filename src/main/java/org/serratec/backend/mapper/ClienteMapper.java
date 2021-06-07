package org.serratec.backend.mapper;

import org.serratec.backend.dto.ClienteDTO;
import org.serratec.backend.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
	
	@Autowired
	EnderecoMapper enderecoMapper;

	public ClienteDTO toDtoPaginaDados(ClienteEntity clienteEntity) {

		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setEmail(clienteEntity.getEmail());
		clienteDto.setUsername(clienteEntity.getUsername());
//		clienteDto.setSenha(clienteEntity.getSenha());
		clienteDto.setNome(clienteEntity.getNome());
		clienteDto.setCpf(clienteEntity.getCpf());
		clienteDto.setTelefone(clienteEntity.getTelefone());
		clienteDto.setDtNascimento(clienteEntity.getDtNascimento());
		clienteDto.setEnderecoList(enderecoMapper.listaEnderecotoDTO(clienteEntity.getEndereco()));

		return clienteDto;
	}
	//
	public ClienteEntity toEntity(ClienteDTO clienteDto) {
		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setEmail(clienteDto.getEmail());
		clienteEntity.setUsername(clienteDto.getUsername());
		clienteEntity.setSenha(clienteDto.getSenha());
		clienteEntity.setNome(clienteDto.getNome());
		clienteEntity.setCpf(clienteDto.getCpf());
		clienteEntity.setTelefone(clienteDto.getTelefone());
		clienteEntity.setDtNascimento(clienteDto.getDtNascimento());
		return clienteEntity;
	}
	public ClienteDTO toDto(ClienteEntity clienteEntity) {
		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setEmail(clienteEntity.getEmail());
		clienteDto.setUsername(clienteEntity.getUsername());
		clienteDto.setToken(clienteEntity.getToken());
		return clienteDto;
	}
}
