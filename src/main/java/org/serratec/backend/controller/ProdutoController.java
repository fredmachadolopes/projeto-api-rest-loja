package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.ProdutoDTO;
import org.serratec.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping("{/listarProdutos}")
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		return new ResponseEntity<List<ProdutoDTO>>(produtoService.findAll(), HttpStatus.OK);
	}

	@PostMapping("/criarProduto")
	public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO dto) {
		return new ResponseEntity<ProdutoDTO>(produtoService.create(dto), HttpStatus.OK);
	}

	@DeleteMapping("/deletarProduto/{id}")
	public ResponseEntity<String> deletarPedido(@PathVariable Long id) {
		return new ResponseEntity<String>(produtoService.delete(id), HttpStatus.OK);
	}

	@PutMapping("/atualizarProduto/{id}")
	public ResponseEntity<ProdutoDTO> atualizarCliente(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
		return new ResponseEntity<ProdutoDTO>(produtoService.update(id, dto), HttpStatus.OK);
	}

}
