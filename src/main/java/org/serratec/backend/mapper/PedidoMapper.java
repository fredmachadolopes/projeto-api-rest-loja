package org.serratec.backend.mapper;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.PedidoDTO;
import org.serratec.backend.entity.PedidoEntity;
import org.serratec.backend.entity.ProdutosPedidosEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {
	
	@Autowired
	ProdutosPedidosMapper pedidosMapper;

	public PedidoDTO toDto(PedidoEntity pedidoEntity) {
		PedidoDTO pedidoDto = new PedidoDTO();
		pedidoDto.setNumeroPedido(pedidoEntity.getNumeroPedido());
		pedidoDto.setListaProdutoPedido(pedidosMapper.produtoPeditoListToDto(pedidoEntity.getProdutosPedidos()));
		pedidoDto.setValorTotalPedido(valorTotalDoPedido(pedidoEntity.getProdutosPedidos()));
		pedidoDto.setDataPedido(pedidoEntity.getDataPedido()); // verificar se é assim que passa a data atual
		pedidoDto.setDataEntrega(pedidoEntity.getDataEntrega());
	    pedidoDto.setStatus(pedidoEntity.getStatus());
	    if(!pedidoEntity.getStatus()) pedidoDto.setAndamento("Finalizado");
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
	
	public Double valorTotalDoPedido(List<ProdutosPedidosEntity> list) {
		Double Total = 0.0;
		for(ProdutosPedidosEntity pedido : list) {
			Total += pedido.getPreco() * pedido.getQuantidade();
		}
		
		return Total ;
	}
	
	public List<PedidoDTO> toDto(List<PedidoEntity> pedidoEntity) {
		System.out.println("teste Mapper produto");
		List<PedidoDTO> pedidoDto = new ArrayList<PedidoDTO>();
	
		for(PedidoEntity pedido : pedidoEntity) {
			pedidoDto.add(toDto(pedido));
		}
		return pedidoDto;
	}

}
