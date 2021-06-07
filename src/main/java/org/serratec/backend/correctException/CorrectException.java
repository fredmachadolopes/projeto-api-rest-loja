package org.serratec.backend.correctException;

import org.serratec.backend.exceptionProject.AddressNotFound;
import org.serratec.backend.exceptionProject.ClientNotFoundException;
import org.serratec.backend.exceptionProject.EmailOrPasswordNotValid;
import org.serratec.backend.exceptionProject.HasAdressInList;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
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
	
	@ExceptionHandler(HasAdressInList.class)
	public ResponseEntity<String> correctExceptionClienteHasAdress(HasAdressInList erro){
		return ResponseEntity.noContent().header("X-erro-msg", erro.getMessage()).build();
	}
	
	@ExceptionHandler(AddressNotFound.class)
	public ResponseEntity<String> notFoundAddress(AddressNotFound erro){
		return ResponseEntity.noContent().header("x-erro-msg", erro.getMessage()).build();
	}
	

	
	
	
	
	

}
