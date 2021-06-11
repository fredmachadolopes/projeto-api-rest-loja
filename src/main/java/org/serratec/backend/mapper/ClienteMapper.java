package org.serratec.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.ClienteDTO;
import org.serratec.backend.entity.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {
	
	@Autowired
	EnderecoMapper enderecoMapper;
	@Autowired
	PedidoMapper pedidoMapper;

	public ClienteDTO toDtoPaginaDados(ClienteEntity clienteEntity) {

		ClienteDTO clienteDto = new ClienteDTO();
		clienteDto.setEmail(clienteEntity.getEmail());
		clienteDto.setUsername(clienteEntity.getUsername());
		clienteDto.setNome(clienteEntity.getNome());
		clienteDto.setCpf(clienteEntity.getCpf());
		clienteDto.setTelefone(clienteEntity.getTelefone());
		clienteDto.setPedidos(pedidoMapper.toDto(clienteEntity.getPedidos()));
		clienteDto.setDtNascimento(clienteEntity.getDtNascimento());
		clienteDto.setEndereco(enderecoMapper.listaEnderecotoDTO(clienteEntity.getEndereco()));

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
	
	public List<ClienteDTO> toListDto(ArrayList<ClienteEntity> clienteEntity){
		List<ClienteDTO> listaClienteDTO = new ArrayList<ClienteDTO>();
		for(ClienteEntity cliente : clienteEntity) {
			
			listaClienteDTO.add(toDtoPaginaDados(cliente));
		}
		return listaClienteDTO;
	}
}
