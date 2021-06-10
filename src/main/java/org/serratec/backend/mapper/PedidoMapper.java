package org.serratec.backend.mapper;

import org.serratec.backend.dto.PedidoDTO;
import org.serratec.backend.entity.PedidoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
	
	@Autowired
	ProdutoMapper produtoMapper;

	public PedidoDTO toDto(PedidoEntity pedidoEntity) {
		PedidoDTO pedidoDto = new PedidoDTO();
		pedidoDto.setNumeroPedido(pedidoEntity.getNumeroPedido());
		pedidoDto.setProdutos(produtoMapper.toDtoList(pedidoEntity.getProdutos()) ); // passa uma lista de produtos
		pedidoDto.setValorTotalPedido(pedidoEntity.getValorTotalPedido());
		pedidoDto.setDataPedido(pedidoEntity.getDataPedido()); // verificar se é assim que passa a data atual
		pedidoDto.setDataEntrega(pedidoEntity.getDataEntrega());
	    pedidoDto.setStatus(pedidoEntity.getStatus());
		return pedidoDto;
	}

	public PedidoEntity toEntity(PedidoDTO pedidoDto) {
		PedidoEntity pedidoEntity = new PedidoEntity();
		pedidoEntity.setNumeroPedido(pedidoDto.getNumeroPedido());
	//	pedidoEntity.setListaProdutoPedido(pedidoDto.getListaProdutoPedido());
		pedidoEntity.setValorTotalPedido(pedidoDto.getValorTotalPedido());
		pedidoEntity.setDataPedido(pedidoDto.getDataPedido());
		pedidoEntity.setDataEntrega(pedidoDto.getDataEntrega());
		pedidoEntity.setStatus(pedidoDto.getStatus());
		return pedidoEntity;
	}

}
