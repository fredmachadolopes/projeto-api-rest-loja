package org.serratec.backend.controller;

import java.util.List;

import org.serratec.backend.dto.EnderecoDTO;
import org.serratec.backend.exceptionProject.AddressNotFound;
import org.serratec.backend.logado.LogarCliente;
import org.serratec.backend.service.EnderecoService;
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
@RequestMapping("/acessoEndereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@PostMapping("/salvarEndereco")
	public ResponseEntity<EnderecoDTO> salvarEndereco(EnderecoDTO endereco){
		return new ResponseEntity<EnderecoDTO>("falta colocar", HttpStatus.OK);
	}
	@GetMapping("/listarEnderecos")
	public ResponseEntity<List<EnderecoDTO>> retornaEnderecos(LogarCliente logado) throws AddressNotFound{
		return new ResponseEntity< List<EnderecoDTO>>(enderecoService.listOfAddress(logado), HttpStatus.OK);
	}
	
	@PutMapping("/datualizarEndereco/{token}")
	public ResponseEntity<String> atualizarEndereco(@PathVariable String token,@RequestBody EnderecoDTO endereco){
		return new ResponseEntity<String>(enderecoService.updateSpecificAddress(endereco, token), HttpStatus.OK);
		//Falta implemetar o token
	}

}
