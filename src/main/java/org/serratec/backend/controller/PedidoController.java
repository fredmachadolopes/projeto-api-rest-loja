package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.PedidoDTO;
import org.serratec.backend.exceptionProject.PedidoNotFound;
import org.serratec.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;

	@GetMapping("/listarPedidos")
	public ResponseEntity<List<PedidoDTO>> findAll() {
		return new ResponseEntity<List<PedidoDTO>>(pedidoService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/criarPedido")
	public ResponseEntity<PedidoDTO> create(@RequestParam(name="cliente") Long id) {
		System.out.println(id);
		return new ResponseEntity<PedidoDTO>(pedidoService.create(id), HttpStatus.OK);
	}

	@DeleteMapping("/deletarPedido/{id}")
	public ResponseEntity<String> deletarPedido(@PathVariable Long id) {
		return new ResponseEntity<String>(pedidoService.delete(id), HttpStatus.OK);
	}
	
	//Rever essa funcionalidade
	@PutMapping("/atualizarPedido/{id}")
	public ResponseEntity<PedidoDTO> atualizarCliente(@PathVariable Long id, @RequestBody PedidoDTO dto) {
		return new ResponseEntity<PedidoDTO>(pedidoService.update(id, dto), HttpStatus.OK);
	}
	
	@PostMapping("/finalizarPedido/{id}")
	public ResponseEntity<PedidoDTO> finalizarPedido(@PathVariable Long id) throws PedidoNotFound{
		return new ResponseEntity<PedidoDTO>(pedidoService.finalizarPedido(id), HttpStatus.OK);
	}
	


}
