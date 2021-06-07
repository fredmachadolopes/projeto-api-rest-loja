package org.serratec.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.serratec.backend.dto.ClienteDTO;
import org.serratec.backend.dto.EnderecoDTO;
import org.serratec.backend.dto.LogarCliente;
import org.serratec.backend.entity.ClienteEntity;
import org.serratec.backend.entity.EnderecoEntity;
import org.serratec.backend.exceptionProject.ClientNotFoundException;
import org.serratec.backend.exceptionProject.EmailOrPasswordNotValid;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.mapper.ClienteMapper;
import org.serratec.backend.mapper.EnderecoMapper;
import org.serratec.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	//ClienteRepository clienteRepository = new ClienteRepository();

	@Autowired
	ClienteMapper clienteMapper;
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	EnderecoMapper enderecoMapper;

	public Boolean verificarId(Long id) {
		return clienteRepository.existsById(id);
	}

	// GET
	public List<ClienteEntity> findAll() {
		return clienteRepository.findAll();
	}
	public String gerarToken() {
		String token ="";
		for(int i = 0; i < 2; i++) {
			char letra = (char) Math.round((Math.random() * 25) + 65);
			int num = (int) Math.round(Math.random() * 9);
			token += num + "" +letra; 
		}
		return token;
	}

	public ClienteDTO findById(Long id) throws ClientNotFoundException {
		if (verificarId(id)) {
			return clienteMapper.toDto(clienteRepository.findById(id).get());
		}
		throw new ClientNotFoundException ("Cliente não encontrado"); 
	}

	// POST
	public ClienteDTO create(ClienteDTO dto) throws HasErrorInResponseCepException{
		ClienteEntity cliente = clienteMapper.toEntity(dto);
		for(EnderecoDTO endercoParaTratar : dto.getEndereco()) {
			 EnderecoEntity endereco = enderecoMapper.dtoToEndereco(enderecoService.adicionandoDadosAoEndereco(endercoParaTratar));
			 cliente.setEndereco(endereco);
			 endereco.setCliente(cliente);
			 enderecoService.saveInDataBase(endereco);
		}
		return clienteMapper.toDto(clienteRepository.save(cliente)) ;
	}

	// PUT
	public ClienteDTO update(Long id, ClienteDTO dto) throws ClientNotFoundException {
		if (verificarId(id)) {
			ClienteEntity cliente = clienteRepository.getById(id);
			if (dto.getEmail() != null) {
				cliente.setEmail(dto.getEmail());
			}
			if (dto.getUsername() != null) {
				cliente.setUsername(dto.getUsername());
			}
			if (dto.getSenha() != null) {
				cliente.setSenha(dto.getSenha());
			}
			if (dto.getNome() != null) {
				cliente.setNome(dto.getNome());
			}

			if (dto.getTelefone() != null) {
				cliente.setTelefone(dto.getTelefone());
			}
			if (dto.getDtNascimento() != null) {
				cliente.setDtNascimento(dto.getDtNascimento());
			}

			// Endereco

			clienteRepository.saveAndFlush(cliente);
			return dto;
		}
		throw new ClientNotFoundException ("Cliente não encontrado");
	}

	// DELETE
	public String delete(Long id) throws ClientNotFoundException {
		if (verificarId(id)) {
			clienteRepository.deleteById(id);
			return "Cliente deletado com sucesso!";
		}
		throw new ClientNotFoundException ("Cliente não encontrado");
	}

	public ClienteDTO logar(LogarCliente dto) throws EmailOrPasswordNotValid {
		for(ClienteEntity cliente: findAll()) {
			if(cliente.getSenha().equals(dto.getSenha()) && (cliente.getEmail().equals(dto.getEmail()))) {
			   cliente.setToken(gerarToken());
			   cliente.setHoraDoToken(LocalDateTime.now());
				return clienteMapper.toDto(clienteRepository.saveAndFlush(cliente));
			}
		}
		throw new EmailOrPasswordNotValid("Email ou senha invalido");
	}
	
	public ClienteDTO recuperarSenha(String email) throws EmailOrPasswordNotValid  {
		String emailCliente = "";
		try {
			emailCliente = clienteRepository.findByEmail(email).getEmail();
		}catch( NullPointerException erro) {
			System.out.println("Erro na busca por E-mail");
		}
		
			
			SimpleEmail	emailEnviar = new SimpleEmail();
			emailEnviar.setHostName("smtp.gmail.com");
			emailEnviar.setSmtpPort(465);
			emailEnviar.setAuthenticator(new DefaultAuthenticator("fred.machado.rj@gmail.com", "senha"));
			emailEnviar.setSSLOnConnect(true);
			
			try {
			
				emailEnviar.setFrom("fred.machado.pt@gmail.com");
				emailEnviar.setSubject("Recuperar senha");
				emailEnviar.setMsg("Testando grupo 1");
				emailEnviar.addTo(emailCliente);
				System.out.println();
				emailEnviar.send();
				System.out.println("Enviado ");
			}catch(EmailException e) {
				System.out.println("houve erro");
				throw new EmailOrPasswordNotValid("Email invalido");
			}
		
		return clienteMapper.toDto(clienteRepository.findByEmail(email));

	}
	
	public ClienteDTO verCliente(String dto) {
		
		ClienteEntity cliente = clienteRepository.findByEmail(dto);

		return  clienteMapper.toDtoPaginaDados(cliente);
	
	}
	
	

}
