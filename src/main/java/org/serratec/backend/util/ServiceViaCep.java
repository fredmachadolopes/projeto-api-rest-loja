package org.serratec.backend.util;


import org.serratec.backend.dto.ViaCepDTO;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceViaCep {
	
	public ViaCepDTO pegarCep(String cep) throws HasErrorInResponseCepException {
		
		try {
			System.out.println(cep);
			HttpEntity<ViaCepDTO> cepApi = new RestTemplate().getForEntity("https://viacep.com.br/ws/"+ cep +"/json", ViaCepDTO.class);
		
				ViaCepDTO cepDados = cepApi.getBody();
			
				return cepDados;

		}catch(HttpClientErrorException e) {
			throw new HasErrorInResponseCepException ("Erro na resposta do via CEP");
		}

	}

}
