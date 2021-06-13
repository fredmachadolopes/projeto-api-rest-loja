package org.serratec.backend.controller;

import java.io.IOException;
import java.util.List;

import org.serratec.backend.dto.ProdutoDTO;
import org.serratec.backend.entity.ImagemEntity;
import org.serratec.backend.exceptionProject.CategoriaIsFalse;
import org.serratec.backend.exceptionProject.ProdutoNotFound;
import org.serratec.backend.service.ImagemService;
import org.serratec.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;
	@Autowired
	ImagemService imagemService;
	
	@GetMapping("/listarProdutos")
	public ResponseEntity<List<ProdutoDTO>> findAll() {
		return new ResponseEntity<List<ProdutoDTO>>(produtoService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/listarPeloNome")
	public ResponseEntity<List<ProdutoDTO>> findAllPeloNome(@RequestParam("nome") String nome) {
		return new ResponseEntity<List<ProdutoDTO>>(produtoService.findAllPeloNome(nome), HttpStatus.OK);
	}

	@PostMapping(path = "create")
	public ResponseEntity<ProdutoDTO> create(@RequestParam MultipartFile file,@RequestPart ProdutoDTO dto) throws CategoriaIsFalse, IOException {
	
		return new ResponseEntity<ProdutoDTO>(produtoService.create(dto, file), HttpStatus.OK);
	}

	@DeleteMapping("/deletarProduto/{identificador}")
	public ResponseEntity<String> deletarPedido(@PathVariable String identificador) throws ProdutoNotFound {
		return new ResponseEntity<String>(produtoService.delete(identificador), HttpStatus.OK);
	}

	@PutMapping("/atualizarProduto")
	public ResponseEntity<ProdutoDTO> atualizarProduto(@RequestBody ProdutoDTO dto) throws ProdutoNotFound {
		return new ResponseEntity<ProdutoDTO>(produtoService.update(dto), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/imagem")
	public ResponseEntity<byte[]> pegarProduto(@PathVariable String id){
		ImagemEntity imagem = imagemService.getImagem(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", imagem.getMimetype());
		headers.add("content-length", String.valueOf(imagem.getData().length));
		return new ResponseEntity<>(imagem.getData(), headers, HttpStatus.OK);
	}

}
