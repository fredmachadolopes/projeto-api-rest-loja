package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.ProdutoDTO;
import org.serratec.backend.entity.CategoriaEntity;
import org.serratec.backend.entity.ProdutoEntity;
import org.serratec.backend.mapper.ProdutoMapper;
import org.serratec.backend.repository.CategoriaRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoMapper produtoMapper;

//
	public Boolean verificarId(Long id) {
		return produtoRepository.existsById(id);
	}

	// GET
//	public List<ProdutoEntity> findAll() {
//		return produtoRepository.findAll();
//	}

	public List<ProdutoDTO> findAll() { // Cliente Logado mostra uma lista de produtos
		List<ProdutoEntity> lista = produtoRepository.findAll();
		List<ProdutoDTO> listaDTO = new ArrayList<ProdutoDTO>();
		for (ProdutoEntity produtoEntity : lista) {
			listaDTO.add(produtoMapper.toDto(produtoEntity));
		}
		return listaDTO;
	}

	public ProdutoDTO findById(Long id) {
		if (verificarId(id)) {
			return produtoMapper.toDto(produtoRepository.findById(id).get());
		}
		return null; // ADICIONAR TRATAMENTO DE ERRO
	}

	// POST
	public ProdutoDTO create(ProdutoDTO dto) {
		ProdutoEntity produto = produtoMapper.toEntity(dto);
		produto.setCategoria(categoriaRepository.getByName(dto.getCategoria()));		
		return produtoMapper.toDto(produtoRepository.save(produto));
	}

	// PUT
	public ProdutoDTO update(Long id, ProdutoDTO dto) {
		if (verificarId(id)) {
			ProdutoEntity produto = produtoRepository.getById(id);
			System.out.println(produto.getId());
			if (dto.getNome() != null) {
				produto.setNome(dto.getNome());
			}
			if (dto.getDescricao() != null) {
				produto.setDescricao(dto.getDescricao());
			}
			if (dto.getPreco() != null) {
				produto.setPreco(dto.getPreco());
			}
			if (dto.getQtdEstoque() != null) {
				produto.setQtdEstoque(dto.getQtdEstoque());
			}

			if (dto.getDtCadastroProduto() != null) {
				produto.setDtCadastroProduto(dto.getDtCadastroProduto()); // deve ser acrescentado os dias
			}

			 return produtoMapper.toDto(produtoRepository.saveAndFlush(produto));
		}
		return null; // ADICIONAR TRATAMENTO DE ERRO
	}

//
	// DELETE
	public String delete(Long id) {

		if (verificarId(id)) {
			produtoRepository.deleteById(id);
			return "Produto deletado com sucesso!";
		}
		return "Produto não encontrado!";
	}

}
