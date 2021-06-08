package org.serratec.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.PedidoDTO;
import org.serratec.backend.entity.PedidoEntity;
import org.serratec.backend.mapper.PedidoMapper;
import org.serratec.backend.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	PedidoMapper pedidoMapper;

	public Boolean verificarId(Long id) {
		return pedidoRepository.existsById(id);
	}

	// GET
//	public List<PedidoEntity> findAll() {
//		return pedidoRepository.findAll();
//	}

	public List<PedidoDTO> findAll() { // Cliente Logado mostra uma lista de pedidos
		List<PedidoEntity> lista = pedidoRepository.findAll();
		List<PedidoDTO> listaDTO = new ArrayList<PedidoDTO>();
		for (PedidoEntity pedidoEntity : lista) {
			listaDTO.add(pedidoMapper.toDto(pedidoEntity));
		}
		return listaDTO;
	}

	public PedidoDTO findById(Long id) {
		if (verificarId(id)) {
			return pedidoMapper.toDto(pedidoRepository.findById(id).get());
		}
		return null; // ADICIONAR TRATAMENTO DE ERRO
	}

	// POST
	public PedidoDTO create(PedidoDTO dto) {
		return pedidoMapper.toDto(pedidoRepository.save(pedidoMapper.toEntity(dto)));
	}

	// PUT
	public PedidoDTO update(Long id, PedidoDTO dto) {
		if (verificarId(id)) {
			PedidoEntity pedido = pedidoMapper.toEntity(this.findById(id));
			if (dto.getNumeroPedido() != null) {
				pedido.setNumeroPedido(dto.getNumeroPedido());
			}
//			if (dto.getListaProdutoPedido() != null) {
//				pedido.setListaProdutoPedido(dto.getListaProdutoPedido()); // pode estar vazia a lista?
//			}
			if (dto.getValorTotalPedido() != null) {
				pedido.setValorTotalPedido(dto.getValorTotalPedido()); // deve ser verificado aqui?
			}
			if (dto.getDataPedido() != null) {
				pedido.setDataPedido(dto.getDataPedido().now()); // deve ser assim?
			}

			if (dto.getDataEntrega() != null) {
				pedido.setDataEntrega(dto.getDataEntrega()); // deve ser acrescentado os dias
			}
			if (dto.getStatus() != null) {
				pedido.setStatus(dto.getStatus()); // deve ser verificado?
			}

			pedidoRepository.saveAndFlush(pedido);

			return dto;
			// return pedidoMapper.toDto(pedidoRepository.save(pedido));
		}
		return null; // ADICIONAR TRATAMENTO DE ERRO
	}

	// DELETE
	public String delete(Long id) { // DEVE HAVER VERIFICAÇÃO COM A TABELA PRODUTOS ESTES DEVEM SER DEVOLVIDOS AO
									// ESTOQUE
		if (verificarId(id)) {
			pedidoRepository.deleteById(id);
			return "Pedido deletado com sucesso!";
		}
		return "Pedido não encontrado!";
	}
}
