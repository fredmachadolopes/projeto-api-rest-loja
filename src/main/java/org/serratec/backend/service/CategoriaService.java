package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.CategoriaDTO;
import org.serratec.backend.entity.CategoriaEntity;
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
			listaDTO.add(categoriaMapper.toDto(categoriaEntity));
		}
		return listaDTO;
	}

	public CategoriaDTO findById(Long id) {
		if (verificarId(id)) {
			return categoriaMapper.toDto(categoriaRepository.findById(id).get());
		}
		return null; // ADICIONAR TRATAMENTO DE ERRO
	}

	// POST
	public CategoriaDTO create(CategoriaDTO dto) {
		return categoriaMapper.toDto(categoriaRepository.save(categoriaMapper.toEntity(dto)));
	}

	// PUT
	public CategoriaDTO update(CategoriaDTO dto) {
		
		
	
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
		
		 // ADICIONAR TRATAMENTO DE ERRO
	}

	// DELETE
	public String delete(CategoriaDTO dto) {
		CategoriaEntity categoria = categoriaRepository.getByIdentificador(dto.getIdentificador());
	
			categoria.setHabilitado(false);
			categoriaRepository.saveAndFlush(categoria);
			return "Categoria deletada com sucesso!";
		
		
	}
}
