package org.serratec.backend.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.ProdutoDTO;
import org.serratec.backend.entity.CategoriaEntity;
import org.serratec.backend.entity.ProdutoEntity;
import org.serratec.backend.exceptionProject.CategoriaIsFalse;
import org.serratec.backend.mapper.ProdutoMapper;
import org.serratec.backend.repository.CategoriaRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ProdutoMapper produtoMapper;
	
	@Autowired
	ImagemService imagemService;
	


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
			ProdutoEntity produto = produtoRepository.findById(id).get();
			return produtoMapper.toDto(produto);
		}
		return null; // ADICIONAR TRATAMENTO DE ERRO
	}

	// POST
	public ProdutoDTO create(ProdutoDTO dto, MultipartFile file) throws CategoriaIsFalse, IOException {
		try {
			
			ProdutoEntity produto = produtoMapper.toEntity(dto);
			CategoriaEntity categoria = categoriaRepository.getByName(dto.getCategoria());
			if(categoria.isHabilitado()) {
				imagemService.create(produto, file);
				produto.setCategoria(categoria);
				return produtoMapper.toDto(produtoRepository.save(produto));
			}
			throw new CategoriaIsFalse("Esta categoria está desabilitada");
		}catch(NullPointerException erro) {
			
			throw new CategoriaIsFalse("Está categoria não existe");
		}
		
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
