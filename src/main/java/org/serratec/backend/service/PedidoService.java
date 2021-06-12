package org.serratec.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.serratec.backend.dto.PedidoDTO;
import org.serratec.backend.entity.ClienteEntity;
import org.serratec.backend.entity.PedidoEntity;
import org.serratec.backend.entity.ProdutoEntity;
import org.serratec.backend.entity.ProdutosPedidosEntity;
import org.serratec.backend.exceptionProject.PedidoNotFound;
import org.serratec.backend.mapper.PedidoMapper;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.repository.PedidoRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	PedidoMapper pedidoMapper;
	@Autowired
	ClienteRepository clienteRepository;

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
	public PedidoDTO create(Long id) {
		// cliente autentidado para pegar ID
		
		ClienteEntity cliente = clienteRepository.getById(id);
		PedidoEntity pedido = new PedidoEntity();
		pedido.setCliente(cliente);
		
		return pedidoMapper.toDto(pedidoRepository.save(pedido));
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


	
	public PedidoDTO finalizarPedido(Long id) throws PedidoNotFound {
		try {
			
			PedidoEntity pedido = pedidoRepository.getByNumeroPedido(id);
//		pedido.setDataEntrega(LocalDate.of(LocalDate.now().getDayOfWeek().getValue() + 5, LocalDate.now().getMonthValue(), LocalDate.now(). ));
			pedido.setDataEntrega(LocalDate.now().plusDays(5));
			pedido.setStatus(false);
			
			for(ProdutosPedidosEntity produtosPedidos : pedido.getProdutosPedidos() ) {
				ProdutoEntity produto = produtoRepository.getById(produtosPedidos.getProduto().getId());
				produto.setQtdEstoque(produtosPedidos.getQuantidade());
				produtosPedidos.setPreco(produtosPedidos.getProduto().getPreco());
				produtoRepository.saveAndFlush(produto);
			}
			
			return pedidoMapper.toDto(pedido);
		}catch(NullPointerException erro) {
			throw new PedidoNotFound("Pedido não encontrado");
		}
	}
}
