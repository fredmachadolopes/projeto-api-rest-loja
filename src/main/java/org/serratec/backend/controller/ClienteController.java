package org.serratec.backend.controller;

import org.serratec.backend.dto.ClienteDTO;
import org.serratec.backend.dto.LogarCliente;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.service.ClienteService;
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
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
//	@GetMapping
//	public ResponseEntity<List<ClienteDTO>>getAll(@RequestParam(name="ordenar", required = false, defaultValue = "id") String nome){
//		return new ResponseEntity<List<ClienteDTO>>(clienteService.findAllCliente(nome),HttpStatus.OK);
//	}
	
	@PostMapping("/criarCliente")
	public ResponseEntity<ClienteDTO>create(@RequestBody ClienteDTO dto) throws HasErrorInResponseCepException{

		return new ResponseEntity<ClienteDTO>(clienteService.create(dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletarCliente/{id}")
	public ResponseEntity<String> deletarCliente(@PathVariable Long id){
		return new ResponseEntity<String>(clienteService.delete(id), HttpStatus.OK);
	}
	
	@PutMapping("/atualizarCliente/{id}")
	public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Long id,@RequestBody ClienteDTO dto){
		return new ResponseEntity<ClienteDTO>(clienteService.update(id, dto),HttpStatus.OK);
	}
	
	@GetMapping("/logar")
	public ResponseEntity<ClienteDTO> logarCliente(@RequestBody LogarCliente dto){
		return new ResponseEntity<ClienteDTO>(clienteService.logar( dto),HttpStatus.OK);
	}
	
	
	
}
