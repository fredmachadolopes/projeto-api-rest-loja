package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.EnderecoDTO;
import org.serratec.backend.exceptionProject.AddressNotFound;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.logado.LogarCliente;
import org.serratec.backend.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/acessoEndereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping("/salvarEndereco")
	public ResponseEntity<String> salvarEndereco(@RequestBody EnderecoDTO endereco, @RequestParam("id") Long trocar) throws HasErrorInResponseCepException{
		
		return new ResponseEntity<String> (enderecoService.adicionarNoCliente(endereco, trocar), HttpStatus.OK);
	}
	@GetMapping("/listarEnderecos")
	public ResponseEntity<List<EnderecoDTO>> retornaEnderecos(@RequestBody LogarCliente logado) throws AddressNotFound{

		return new ResponseEntity< List<EnderecoDTO>>(enderecoService.listOfAddress(logado), HttpStatus.OK);
	}
	
//	@PutMapping("/atualizarEndereco/{token}/{id}")
//	public ResponseEntity<String> atualizarEndereco(@PathVariable String token,@PathVariable Long id,@RequestBody EnderecoDTO endereco){
//		return new ResponseEntity<String>(enderecoService.updateSpecificAddress(endereco, id), HttpStatus.OK);
//		//Falta implemetar o token	
//	}
//	
	@DeleteMapping("/deletarEndereco")
	public ResponseEntity<String> deletarEnderecoEspecifico(@RequestParam("teste") String token, @RequestBody EnderecoDTO dto){
		return new ResponseEntity<String>(enderecoService.deletarEnderecoEspecifico(token, dto), HttpStatus.OK);
	}

}
