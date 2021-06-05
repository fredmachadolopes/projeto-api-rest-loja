package org.serratec.backend.correctException;

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

}
