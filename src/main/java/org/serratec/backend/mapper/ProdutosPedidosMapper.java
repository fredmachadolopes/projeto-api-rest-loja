package org.serratec.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.CompraDTO;
import org.serratec.backend.entity.ProdutosPedidosEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutosPedidosMapper {
	
	public CompraDTO produtoPeditoToDto(ProdutosPedidosEntity pedido) {
		
		CompraDTO compra = new CompraDTO();
		compra.setProduto(pedido.getProduto().getNome());
		compra.setQuantidade(pedido.getQuantidade());
		compra.setValor(pedido.getPreco() * pedido.getQuantidade());
		return compra;
	
    }
	
	
	public List<CompraDTO> produtoPeditoListToDto(List<ProdutosPedidosEntity> pedido) {
		
		List<CompraDTO> compra = new ArrayList<CompraDTO>();
		
		for(ProdutosPedidosEntity pedidoLista : pedido) {
			compra.add(produtoPeditoToDto(pedidoLista));
		}
		return compra;
	
    }
		

		
		
		
		

}
