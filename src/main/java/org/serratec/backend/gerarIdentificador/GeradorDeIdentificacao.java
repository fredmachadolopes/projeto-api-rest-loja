package org.serratec.backend.gerarIdentificador;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeradorDeIdentificacao {
	
	@Bean
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
