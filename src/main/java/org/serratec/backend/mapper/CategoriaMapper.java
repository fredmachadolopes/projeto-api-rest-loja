package org.serratec.backend.mapper;

import org.serratec.backend.dto.CategoriaDTO;
import org.serratec.backend.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

	public CategoriaDTO toDto(CategoriaEntity categoriaEntity) {

		CategoriaDTO categoriaDto = new CategoriaDTO();

		categoriaDto.setNome(categoriaEntity.getNome());
		categoriaDto.setDescricao(categoriaEntity.getDescricao());

		return categoriaDto;
	}

	public CategoriaEntity toEntity(CategoriaDTO categoriaDto) {
		CategoriaEntity categoriaEntity = new CategoriaEntity();

		categoriaEntity.setNome(categoriaDto.getNome());
		categoriaEntity.setDescricao(categoriaDto.getDescricao());

		return categoriaEntity;
	}
}
