package org.serratec.backend.controller;

import org.serratec.backend.dto.CompraDTO;
import org.serratec.backend.exceptionProject.ItemNaoEncontrado;
import org.serratec.backend.exceptionProject.ProdutosPedidosErro;
import org.serratec.backend.service.ProdutosPedidosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtosPedidos")
public class ProdutosPedidosController {
	
	@Autowired
	ProdutosPedidosService compraService;
	
	@PostMapping("/criarPedido")
	public  ResponseEntity<String> criarPedido(@RequestBody CompraDTO compra) throws ProdutosPedidosErro {
		return new ResponseEntity<String>(compraService.iniciarPedido(compra), HttpStatus.OK);
	}
	
	@GetMapping("/verPedido/{id}")
	public ResponseEntity<CompraDTO> verPedido(@PathVariable Long id){
		return new ResponseEntity<CompraDTO>(compraService.verPedido(id), HttpStatus.OK);
	}
	
	@PutMapping("/atualizarPedido/{id}")
	public ResponseEntity<String> atualizarPedido(@RequestBody CompraDTO pedido,@PathVariable Long id){
		return new ResponseEntity<String>(compraService.atualizarPedido(pedido, id), HttpStatus.OK);
	}
	
	@DeleteMapping("/deletarPedido/{id}")
	public ResponseEntity<String> deletarPedido(@PathVariable Long id) throws ItemNaoEncontrado{
		return new ResponseEntity<String>(compraService.deletarPedido(id), HttpStatus.OK);
	}
	
	

}
