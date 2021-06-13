package org.serratec.backend.mapper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.CompraDTO;
import org.serratec.backend.entity.ProdutosPedidosEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutosPedidosMapper {
	
	public CompraDTO produtoPeditoToDto(ProdutosPedidosEntity pedido) {
		DecimalFormat df = new DecimalFormat("0.##");
		CompraDTO compra = new CompraDTO();
		compra.setProduto(pedido.getProduto().getNome());
		compra.setQuantidade(pedido.getQuantidade());
		compra.setNumeroPedido((long) pedido.getPedido().getNumeroPedido());
		compra.setCodigoProduto(pedido.getProduto().getCodigoProduto());
		
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
