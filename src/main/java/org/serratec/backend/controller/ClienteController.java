package org.serratec.backend.controller;

import org.serratec.backend.dto.ClienteDTO;
import org.serratec.backend.exceptionProject.ClientNotFoundException;
import org.serratec.backend.exceptionProject.EmailOrPasswordNotValid;
import org.serratec.backend.exceptionProject.ErroNaEntradaDosDados;
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
	
	@PostMapping("/criarCliente")
	public ResponseEntity<ClienteDTO>create(@RequestBody ClienteDTO dto) throws HasErrorInResponseCepException, ErroNaEntradaDosDados{
		return new ResponseEntity<ClienteDTO>(clienteService.create(dto),HttpStatus.OK);
	}
	
	@DeleteMapping("/deletarCliente")
	public ResponseEntity<String> deletarCliente(@RequestParam("identificador")String identificador) throws ClientNotFoundException{
		// delete lógico funcionando mas precisa emplementar o token
		return new ResponseEntity<String>(clienteService.delete(identificador), HttpStatus.OK);
		
	}
	
	@PutMapping("/atualizarCliente")
	public ResponseEntity<ClienteDTO> atualizarCliente(@RequestBody ClienteDTO dto) throws ClientNotFoundException{
	
		return new ResponseEntity<ClienteDTO>(clienteService.update(dto),HttpStatus.OK);
	}
	
	@GetMapping("/logar")
	public ResponseEntity<ClienteDTO> logarCliente(@RequestBody LogarCliente dto) throws EmailOrPasswordNotValid, NullPointerException{
		// Logar funcionando mas teremos que adicionar o novo token
		return new ResponseEntity<ClienteDTO>(clienteService.logar( dto),HttpStatus.OK);
	}
	
	@GetMapping("/recuperarSenha")
	public ResponseEntity<ClienteDTO> logarCliente(@RequestParam(name = "email") String email) throws EmailOrPasswordNotValid{
		String mensagem = "http://localhost:8080/cliente/atualizarCliente \n Entre no link acima e altere sua senha";
		return new ResponseEntity<ClienteDTO>(clienteService.recuperarSenha(email, mensagem),HttpStatus.OK);
	}
	
	@GetMapping("/dadosLogado")
	public ResponseEntity<ClienteDTO> dadosLogado(@RequestParam(name = "email") String dto){
		return new ResponseEntity<ClienteDTO>(clienteService.verCliente(dto), HttpStatus.OK);
		
	}
	
	
	
}
