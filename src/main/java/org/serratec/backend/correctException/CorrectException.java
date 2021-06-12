package org.serratec.backend.correctException;

import org.serratec.backend.exceptionProject.AddressNotFound;
import org.serratec.backend.exceptionProject.CategoriaIsFalse;
import org.serratec.backend.exceptionProject.ClientNotFoundException;
import org.serratec.backend.exceptionProject.EmailOrPasswordNotValid;
import org.serratec.backend.exceptionProject.ErroNaEntradaDosDados;
import org.serratec.backend.exceptionProject.HasAdressInList;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.exceptionProject.NaoHaEnderecoComEsseIdentificador;
import org.serratec.backend.exceptionProject.PedidoNotFound;
import org.serratec.backend.exceptionProject.ProdutosPedidosErro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CorrectException {
	
	@ExceptionHandler(HasErrorInResponseCepException.class)
	public ResponseEntity<String> correctExceptionInCep(HasErrorInResponseCepException erro) {
		return   ResponseEntity.notFound().header("x-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<String> correctExceptionCliente(ClientNotFoundException erro){
		return ResponseEntity.notFound().header("X-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(EmailOrPasswordNotValid.class)
	public ResponseEntity<String> correctExceptionCliente(EmailOrPasswordNotValid erro){
		return ResponseEntity.noContent().header("X-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(ProdutosPedidosErro.class)
	public ResponseEntity<String> produtosPedidosFaltando(ProdutosPedidosErro erro){
		return ResponseEntity.noContent().header("X-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(PedidoNotFound.class)
	public ResponseEntity<String> pedidoNotFound(PedidoNotFound erro){
		return ResponseEntity.noContent().header("X-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(HasAdressInList.class)
	public ResponseEntity<String> correctExceptionClienteHasAdress(HasAdressInList erro){
		return ResponseEntity.noContent().header("X-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(AddressNotFound.class)
	public ResponseEntity<String> notFoundAddress(AddressNotFound erro){
		return ResponseEntity.noContent().header("x-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(ErroNaEntradaDosDados.class)
	public ResponseEntity<String> dadosIncorretos(ErroNaEntradaDosDados erro){
		return ResponseEntity.noContent().header("x-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(CategoriaIsFalse.class)
	public ResponseEntity<String> categoriaDeletada(CategoriaIsFalse erro){
		return ResponseEntity.noContent().header("x-erro-msg", erro.getMessage()).build();
	}
	
	
	
	@ExceptionHandler(NaoHaEnderecoComEsseIdentificador.class)
	public ResponseEntity<String> notFoundAddress(NaoHaEnderecoComEsseIdentificador erro){
		return ResponseEntity.noContent().header("x-erro-msg", erro.getMessage()).build();
	}
	
	
	

	
	
	
	
	

}
