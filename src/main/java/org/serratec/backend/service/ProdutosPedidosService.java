package org.serratec.backend.service;

import java.util.List;
import org.serratec.backend.entity.PedidoEntity;
import org.serratec.backend.entity.ProdutoEntity;
import org.serratec.backend.entity.ProdutosPedidosEntity;
import org.serratec.backend.repository.PedidoRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutosPedidosService {
	
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	ProdutosPedidosRepository produtosPedidosRepository;
	
	public String adiconarProdutoNalista(Long idClienteTrocar, String produto,int quantidade, Long idLista) {
		
		PedidoEntity pedido = pedidoRepository.getById(idLista);
		ProdutoEntity produtoPedido = produtoRepository.getByName(produto);
		ProdutosPedidosEntity adicionarProduto = new ProdutosPedidosEntity();
		
	
		
		if(pedido.getStatus()) {
			adicionarProduto.setPedido(pedido);
			adicionarProduto.setProduto(produtoPedido);
			adicionarProduto.setQuantidade(quantidade);
			adicionarProduto.setPreco(produtoPedido.getPreco());
			produtosPedidosRepository.save(adicionarProduto);
			return "Produto adicionado na lista";
		}
		
		
		PedidoEntity pedidoLista = new PedidoEntity();
		adicionarProduto.setPedido(pedidoLista);
		adicionarProduto.setProduto(produtoPedido);
		adicionarProduto.setQuantidade(quantidade);
		pedidoRepository.save(pedidoLista);
		adicionarProduto.setPreco(produtoPedido.getPreco());
		produtosPedidosRepository.save(adicionarProduto);
		return "Produto adicionado na lista";
		
	}

}
