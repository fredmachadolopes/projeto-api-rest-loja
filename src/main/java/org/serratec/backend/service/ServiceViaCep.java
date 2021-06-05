package org.serratec.backend.service;

import org.serratec.backend.entity.ViaCepEntity;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceViaCep {
	
	public ViaCepEntity pegarCep(String cep) throws HasErrorInResponseCepException {
		
		try {
			
			HttpEntity<ViaCepEntity> cepApi = new RestTemplate().getForEntity("https://viacep.com.br/ws/"+ cep +"/json", ViaCepEntity.class);
		
				ViaCepEntity cepDados = cepApi.getBody();
			
				return cepDados;

		}catch(HttpClientErrorException e) {
			throw new HasErrorInResponseCepException ("Erro na resposta do via CEP");
		}

	}

}
