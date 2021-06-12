package org.serratec.backend.mapper;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.ProdutoDTO;
import org.serratec.backend.entity.ProdutoEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class ProdutoMapper {

	public ProdutoDTO toDto(ProdutoEntity produtoEntity) {
        
		ProdutoDTO produtoDto = new ProdutoDTO();
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produto/{id}/imagem/")
				.buildAndExpand(produtoEntity.getId()).toUri();
		produtoDto.setNome(produtoEntity.getNome());
		produtoDto.setDescricao(produtoEntity.getDescricao());
		produtoDto.setPreco(produtoEntity.getPreco());
		produtoDto.setQtdEstoque(produtoEntity.getQtdEstoque());
		produtoDto.setCategoria(produtoEntity.getCategoria().getNome());
		produtoDto.setDtCadastroProduto(produtoEntity.getDtCadastroProduto());
		produtoDto.setUrl(uri.toString());
		produtoDto.setCodigoProduto(produtoEntity.getCodigoProduto());
		return produtoDto;
	}

	//
	public ProdutoEntity toEntity(ProdutoDTO produtoDto) {

		ProdutoEntity produtoEntity = new ProdutoEntity();
		produtoEntity.setCodigoProduto(produtoDto.getCodigoProduto());
		produtoEntity.setNome(produtoDto.getNome());
		produtoEntity.setDescricao(produtoDto.getDescricao());
		produtoEntity.setPreco(produtoDto.getPreco());
		produtoEntity.setQtdEstoque(produtoDto.getQtdEstoque());
//		produtoEntity.setDtCadastroProduto(produtoDto.getDtCadastroProduto());

		return produtoEntity;
	}
	
	public List<ProdutoDTO> toDtoList(List<ProdutoEntity> produtoEntity) {

		List<ProdutoDTO> produtoDto = new ArrayList<ProdutoDTO>();
		for(ProdutoEntity produto : produtoEntity) {
			produtoDto.add(toDto(produto));
		}
		
		return produtoDto;
	}
}
