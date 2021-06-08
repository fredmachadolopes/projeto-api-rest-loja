package org.serratec.backend.mapper;

import org.serratec.backend.dto.ProdutoDTO;
import org.serratec.backend.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

	public ProdutoDTO toDto(ProdutoEntity produtoEntity) {

		ProdutoDTO produtoDto = new ProdutoDTO();
		//
		produtoDto.setNome(produtoEntity.getNome());
		produtoDto.setDescricao(produtoEntity.getDescricao());
		produtoDto.setPreco(produtoEntity.getPreco());
		produtoDto.setQtdEstoque(produtoEntity.getQtdEstoque());
		produtoDto.setDtCadastroProduto(produtoEntity.getDtCadastroProduto());
		// não coloquei a imagem ***
		return produtoDto;
	}

	//
	public ProdutoEntity toEntity(ProdutoDTO produtoDto) {

		ProdutoEntity produtoEntity = new ProdutoEntity();
		//
		produtoEntity.setNome(produtoDto.getNome());
		produtoEntity.setDescricao(produtoDto.getDescricao());
		produtoEntity.setPreco(produtoDto.getPreco());
		produtoEntity.setQtdEstoque(produtoDto.getQtdEstoque());
		produtoEntity.setDtCadastroProduto(produtoDto.getDtCadastroProduto());

		return produtoEntity;
	}
}
