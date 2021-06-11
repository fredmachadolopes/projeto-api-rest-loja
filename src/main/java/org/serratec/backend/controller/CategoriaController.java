package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.CategoriaDTO;
import org.serratec.backend.exceptionProject.CategoriaIsFalse;
import org.serratec.backend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	@PostMapping("/adicionarCategoria")
	public ResponseEntity<CategoriaDTO> adicionarCategoria(@RequestBody CategoriaDTO categoria){
		return new ResponseEntity<CategoriaDTO>(categoriaService.create(categoria), HttpStatus.OK);
	}
	
	@GetMapping("/listarCategorias")
	public ResponseEntity<List<CategoriaDTO>> listarCategorias(){
		return new ResponseEntity<List<CategoriaDTO>>(categoriaService.findAll(), HttpStatus.OK);
	}
	
	@PutMapping("/atualizarCategoria")
	public ResponseEntity<CategoriaDTO> atualizarCategoria(@RequestBody CategoriaDTO categoria) throws CategoriaIsFalse{
		return new  ResponseEntity<CategoriaDTO>( categoriaService.update(categoria), HttpStatus.OK);
	}
	
	@DeleteMapping("deletar")
	public ResponseEntity<String> deletarCategoria(@RequestBody CategoriaDTO categoria) throws CategoriaIsFalse{
		return new ResponseEntity<String>(categoriaService.delete(categoria), HttpStatus.OK);
	}
}
