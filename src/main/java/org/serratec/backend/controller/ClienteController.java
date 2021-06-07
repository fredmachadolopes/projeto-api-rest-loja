package org.serratec.backend.controller;

import org.serratec.backend.dto.ClienteDTO;
import org.serratec.backend.exceptionProject.ClientNotFoundException;
import org.serratec.backend.exceptionProject.EmailOrPasswordNotValid;
import org.serratec.backend.exceptionProject.HasAdressInList;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.logado.LogarCliente;
import org.serratec.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@DeleteMapping("/deletarCliente")
	public ResponseEntity<String> deletarCliente(@RequestBody LogarCliente logado) throws ClientNotFoundException{
		return new ResponseEntity<String>(clienteService.delete(logado), HttpStatus.OK);
	}
	
	@PutMapping("/atualizarCliente")
	public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO dto) throws ClientNotFoundException{
		return new ResponseEntity<ClienteDTO>(clienteService.update(dto),HttpStatus.OK);
	}
	
	@GetMapping("/logar")
	public ResponseEntity<ClienteDTO> logarCliente(@RequestBody LogarCliente dto) throws EmailOrPasswordNotValid, NullPointerException{
		return new ResponseEntity<ClienteDTO>(clienteService.logar( dto),HttpStatus.OK);
	}
	
	@GetMapping("/recuperarSenha")
	public ResponseEntity<ClienteDTO> logarCliente(@RequestParam(name = "email") String email) throws EmailOrPasswordNotValid{
		return new ResponseEntity<ClienteDTO>(clienteService.recuperarSenha(email),HttpStatus.OK);
	}
	
	@GetMapping("/dadosLogado")
	public ResponseEntity<ClienteDTO> dadosLogado(@RequestParam(name = "email") String dto){
	
		return new ResponseEntity<ClienteDTO>(clienteService.verCliente(dto), HttpStatus.OK);
		
	}
	
	
	
}
