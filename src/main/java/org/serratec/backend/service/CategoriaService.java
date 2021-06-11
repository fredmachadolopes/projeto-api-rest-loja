package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.CategoriaDTO;
import org.serratec.backend.entity.CategoriaEntity;
import org.serratec.backend.exceptionProject.CategoriaIsFalse;
import org.serratec.backend.mapper.CategoriaMapper;
import org.serratec.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	CategoriaMapper categoriaMapper;

	public Boolean verificarId(Long id) {
		return categoriaRepository.existsById(id);
	}

	// GET
	public List<CategoriaDTO> findAll() { // Cliente Logado mostra uma lista de produtos
		
		List<CategoriaEntity> lista = categoriaRepository.findAll();
		List<CategoriaDTO> listaDTO = new ArrayList<CategoriaDTO>();
		for (CategoriaEntity categoriaEntity : lista) {
			if(categoriaEntity.isHabilitado()) {
				listaDTO.add(categoriaMapper.toDto(categoriaEntity));	
			}
		}
		return listaDTO;
	}

//	public CategoriaDTO findById(Long id) {
//		if (verificarId(id)) {
//			return categoriaMapper.toDto(categoriaRepository.findById(id).get());
//		}
//		return null; // ADICIONAR TRATAMENTO DE ERRO
//	}

	// POST
	public CategoriaDTO create(CategoriaDTO dto) {
		return categoriaMapper.toDto(categoriaRepository.save(categoriaMapper.toEntity(dto)));
	}

	// PUT
	public CategoriaDTO update(CategoriaDTO dto) throws CategoriaIsFalse {
		try {
			
			CategoriaEntity categoria = categoriaRepository.getByIdentificador(dto.getIdentificador());
			if (dto.getNome() != null) {
				categoria.setNome(dto.getNome());
			}
			if (dto.getDescricao() != null) {
				categoria.setDescricao(dto.getDescricao());
			}
			categoria.setHabilitado(true);
			
			categoriaRepository.saveAndFlush(categoria);
			return dto;
		}catch(NullPointerException erro) {
			throw new CategoriaIsFalse("Identificador incorreto ou a categoria não existe");
		}

		// ADICIONAR TRATAMENTO DE ERRO
	}

	// DELETE
	public String delete(CategoriaDTO dto) throws CategoriaIsFalse {
		try {

			CategoriaEntity categoria = categoriaRepository.getByIdentificador(dto.getIdentificador());
			if(categoria.getProduto().size() <= 0) {
				categoria.setHabilitado(false);
				categoriaRepository.saveAndFlush(categoria);
				return "Categoria deletada com sucesso!";
				
			}
			
			return "Esta categoria contém produtos associados, não pode ser deletada!";
			
		} catch (NullPointerException erro) {
			throw new CategoriaIsFalse("Nome incorreto ou categoria não existe");
		}

	}
}
