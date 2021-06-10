package org.serratec.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.CategoriaDTO;
import org.serratec.backend.entity.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
	
	@Autowired
	ProdutoMapper produtoMapper;

	public CategoriaDTO toDto(CategoriaEntity categoriaEntity) {

		CategoriaDTO categoriaDto = new CategoriaDTO();
		categoriaDto.setNome(categoriaEntity.getNome());
		categoriaDto.setIdentificador(categoriaEntity.getIdentificador());
		categoriaDto.setProduto(produtoMapper.toDtoList(categoriaEntity.getProduto()));
		categoriaDto.setDescricao(categoriaEntity.getDescricao());

		return categoriaDto;
	}

	public CategoriaEntity toEntity(CategoriaDTO categoriaDto) {
		CategoriaEntity categoriaEntity = new CategoriaEntity();
		categoriaEntity.setNome(categoriaDto.getNome());
		categoriaEntity.setDescricao(categoriaDto.getDescricao());

		return categoriaEntity;
	}
	public List<CategoriaDTO> toDtoList(ArrayList<CategoriaEntity> categoriaEntity) {
		List<CategoriaDTO> listaDTO = new ArrayList<CategoriaDTO>();
		
		for(CategoriaEntity categoria : categoriaEntity) {
			listaDTO.add(toDto(categoria));
		}
		
		return listaDTO;
	}
	
}
