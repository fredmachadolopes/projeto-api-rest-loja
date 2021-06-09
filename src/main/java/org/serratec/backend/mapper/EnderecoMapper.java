package org.serratec.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.EnderecoDTO;
import org.serratec.backend.entity.EnderecoEntity;
import org.springframework.stereotype.Component;
@Component
public class EnderecoMapper {

	public EnderecoDTO enderecoToDto(EnderecoEntity endereco) {
		EnderecoDTO dto = new EnderecoDTO();
		
		dto.setCEP(endereco.getCEP());
		dto.setRua(endereco.getRua());
		dto.setBairro(endereco.getBairro());
		dto.setCidade(endereco.getCidade());
		dto.setNumero(endereco.getNumero());
//		dto.setToken(endereco.getToken());
		dto.setComplemento(endereco.getComplemento());
		dto.setEstado(endereco.getEstado());
		
		return dto;
	}
	
	public EnderecoEntity dtoToEndereco(EnderecoDTO enderecoEntrada) {
		EnderecoEntity enderecoSaida = new EnderecoEntity();
		
		enderecoSaida.setCEP(enderecoEntrada.getCEP());
		enderecoSaida.setRua(enderecoEntrada.getRua());
		enderecoSaida.setBairro(enderecoEntrada.getBairro());
		enderecoSaida.setCidade(enderecoEntrada.getCidade());
		enderecoSaida.setNumero(enderecoEntrada.getNumero());
		enderecoSaida.setComplemento(enderecoEntrada.getComplemento());
		
		enderecoSaida.setEstado(enderecoEntrada.getEstado());
		
		return enderecoSaida;
	}
	
	public List<EnderecoDTO> listaEnderecotoDTO(List<EnderecoEntity> list){
		
		List<EnderecoDTO> lista = new ArrayList<EnderecoDTO>();
		for(EnderecoEntity enderecoFor : list) {
			lista.add(enderecoToDto(enderecoFor));
			
		}
		
		return lista;
	}

}
