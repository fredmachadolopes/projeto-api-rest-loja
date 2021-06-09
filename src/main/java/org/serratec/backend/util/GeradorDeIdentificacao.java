package org.serratec.backend.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class GeradorDeIdentificacao {
	
	
	public String retornaIdentificador() {
		
			String token = "";
			for (int i = 0; i < 3; i++) {
				char letra = (char) Math.round((Math.random() * 25) + 65);
				int num = (int) Math.round(Math.random() * 9);
				token += num + "" + letra;
			}
			return token;
		
	}
}
