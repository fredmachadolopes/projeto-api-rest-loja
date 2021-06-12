package org.serratec.backend.service;

import org.serratec.backend.dto.CompraDTO;
import org.serratec.backend.entity.PedidoEntity;
import org.serratec.backend.entity.ProdutoEntity;
import org.serratec.backend.entity.ProdutosPedidosEntity;
import org.serratec.backend.exceptionProject.ItemNaoEncontrado;
import org.serratec.backend.exceptionProject.ProdutosPedidosErro;
import org.serratec.backend.repository.PedidoRepository;
import org.serratec.backend.repository.ProdutoRepository;
import org.serratec.backend.repository.ProdutosPedidosRepository;
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
	//retorno provisorio
	public String iniciarPedido(CompraDTO compra) throws ProdutosPedidosErro {
		//codigoProduto
		for(ProdutosPedidosEntity adicionarProduto : produtosPedidosRepository.findAll()) {
			if(adicionarProduto.getProduto().getCodigoProduto().equals(compra.getCodigoProduto())){
				//ProdutoEntity produtoPedido = produtoRepository.getByCodigoProduto(compra.getProduto());
				if(adicionarProduto.getQuantidade() + compra.getQuantidade() <= adicionarProduto.getProduto().getQtdEstoque()) {
					adicionarProduto.setQuantidade(adicionarProduto.getQuantidade() + compra.getQuantidade()); 
					return "Seu pedido foi adicionado na lista";
				}
				throw new ProdutosPedidosErro("A quantidade desse produto em estoque é de apenas " +adicionarProduto.getProduto().getQtdEstoque()+ 
						" e seu pedido já está com " + adicionarProduto.getQuantidade());
			}
		}
		ProdutoEntity produtoPedido = produtoRepository.getByName(compra.getProduto());
		if(compra.getQuantidade() <= produtoPedido.getQtdEstoque()) {
			ProdutosPedidosEntity adicionarProduto = new ProdutosPedidosEntity();
			adicionarProduto.setPedido(pedidoRepository.getByNumeroPedido(compra.getNumeroPedido()));
			adicionarProduto.setProduto(produtoPedido);
			adicionarProduto.setQuantidade(compra.getQuantidade());
			adicionarProduto.setPreco(produtoPedido.getPreco());
			produtosPedidosRepository.save(adicionarProduto);
			return "Produto adicionado na lista";	
		}
		
		throw new ProdutosPedidosErro("Quantidade do produto em estoque e menor que seu pedido");
	}
	
	
	public String adiconarProdutoNalista(CompraDTO compra, Long id) {
		
		PedidoEntity pedido = pedidoRepository.getById(id);
		ProdutoEntity produtoPedido = produtoRepository.getByName(compra.getProduto());
		ProdutosPedidosEntity adicionarProduto = new ProdutosPedidosEntity();
		
	
		
		if(pedido.getStatus()) {
			adicionarProduto.setPedido(pedido);
			adicionarProduto.setProduto(produtoPedido);
			adicionarProduto.setQuantidade(compra.getQuantidade());
			adicionarProduto.setPreco(produtoPedido.getPreco());
			produtosPedidosRepository.save(adicionarProduto);
			return "Produto adicionado na lista " + " produto: "+ produtoPedido.getNome() +" preço "+ produtoPedido.getPreco();
		}
		
		
		PedidoEntity pedidoLista = new PedidoEntity();
		adicionarProduto.setPedido(pedidoLista);
		adicionarProduto.setProduto(produtoPedido);
		adicionarProduto.setQuantidade(1);
		pedidoRepository.save(pedidoLista);
		adicionarProduto.setPreco(produtoPedido.getPreco());
		produtosPedidosRepository.save(adicionarProduto);
		return "Produto adicionado na lista " + " produto: "+ produtoPedido.getNome() +" preço "+ produtoPedido.getPreco();
		
	}


	public String atualizarPedido(CompraDTO pedido, Long id) {
		System.out.println(pedido.getQuantidade());
		ProdutosPedidosEntity produtoPedido =  produtosPedidosRepository.getById(id);
		if(pedido.getQuantidade() != null) {	
			produtoPedido.setQuantidade(pedido.getQuantidade());
			produtosPedidosRepository.saveAndFlush(produtoPedido);
			return "Atualizado com sucesso" + " produto: "+ produtoPedido.getProduto().getNome() +" preço "+ produtoPedido.getPreco();
		}
		
		return "Nesse acesso só se atualiza quantidade";
	}
	
	public CompraDTO verPedido(Long id) {

		ProdutosPedidosEntity produtoPedido =  produtosPedidosRepository.getById(id);
		CompraDTO pedido = new CompraDTO();
		pedido.setProduto(produtoPedido.getProduto().getNome());
		pedido.setQuantidade(produtoPedido.getQuantidade());
		pedido.setValor(produtoPedido.getPreco() * produtoPedido.getQuantidade() );
		
			return  pedido;
			// Temos de implementar lógica
	}


	public String deletarPedido(Long id) throws ItemNaoEncontrado {
		try {
			
			produtosPedidosRepository.deleteById(id);
		}catch(NullPointerException erro) {
			throw new ItemNaoEncontrado("Esse item não pode ser localizado, tente outro id");
		}
		return "Item deletado com sucesso";
	}

}
