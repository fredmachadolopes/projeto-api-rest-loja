package org.serratec.backend.service;


import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.serratec.backend.dto.ClienteDTO;
import org.serratec.backend.dto.EnderecoDTO;
import org.serratec.backend.entity.ClienteEntity;
import org.serratec.backend.entity.EnderecoEntity;
import org.serratec.backend.exceptionProject.ClientNotFoundException;
import org.serratec.backend.exceptionProject.EmailOrPasswordNotValid;
import org.serratec.backend.exceptionProject.ErroNaEntradaDosDados;
import org.serratec.backend.exceptionProject.HasErrorInResponseCepException;
import org.serratec.backend.logado.LogarCliente;
import org.serratec.backend.mapper.ClienteMapper;
import org.serratec.backend.mapper.EnderecoMapper;
import org.serratec.backend.repository.ClienteRepository;
import org.serratec.backend.util.GeradorDeIdentificacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	GeradorDeIdentificacao gerardorId;
	
	@Autowired
	ClienteMapper clienteMapper;

	@Autowired
	EnderecoService enderecoService;

	@Autowired
	EnderecoMapper enderecoMapper;

	// GET
	public List<ClienteEntity> findAll() {
		List<ClienteEntity> clienteLista = clienteRepository.findAll();
		return clienteLista;
	}


	// POST
	public ClienteDTO create(ClienteDTO dto) throws HasErrorInResponseCepException, ErroNaEntradaDosDados {
		for(ClienteEntity clienteDoDB : findAll()) {
			if(clienteDoDB.getCpf().equals(dto.getCpf())) {
				throw new ErroNaEntradaDosDados("CPF já cadastrado");
			}
		}
		
		if(dto.getTelefone().length() >= 8 && dto.getCpf().length() == 14 ) {
			
			ClienteEntity cliente = clienteMapper.toEntity(dto);
			
			
			for (EnderecoDTO endercoParaTratar : dto.getEndereco()) {
				EnderecoEntity endereco = enderecoMapper
						.dtoToEndereco(enderecoService.adicionandoDadosAoEndereco(endercoParaTratar));
				//cliente.setEndereco(endereco);
				endereco.setCliente(cliente);
				enderecoService.saveInDataBase(endereco);
			}
			return clienteMapper.toDto(cliente);
		}
		
		throw new ErroNaEntradaDosDados("Seu CPF tem de estar no formato xxx.xxx.xxx-xx e seu telefone deve ter mais de 8 digitos");
	}

	// PUT
	public ClienteDTO update(ClienteDTO dto) throws ClientNotFoundException {
		
		for (ClienteEntity cliente : findAll()) {
			if (cliente.getToken().equals(dto.getToken()) && (cliente.getEmail().equals(dto.getEmail()))) {
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
				System.out.println("passou");
				clienteRepository.saveAndFlush(cliente);
				return clienteMapper.toDto(cliente);
			}
		}
		throw new ClientNotFoundException("Cliente não encontrado");
	}

	// Esse metodo é para saber se o cliente está logado
	public ClienteEntity clienteLogado(LogarCliente logado) {
		try {
			for (ClienteEntity cliente : findAll()) {
				
			if (cliente.getToken().equals(logado.getToken()) && (cliente.getEmail().equals(logado.getEmail()))) {

				return cliente;
			}
		}
		}catch(NullPointerException erro) {
			System.out.println("Senha ou email inválido");
		}
		return null;
	}

	// DELETE
	public String delete(LogarCliente logado) throws ClientNotFoundException {
		ClienteEntity cliente = clienteLogado(logado);
		if (cliente != null) {

			cliente.setHabilitado(false);
			clienteRepository.saveAndFlush(cliente);
			return "Cliente deletado com sucesso!";
		}

		throw new ClientNotFoundException("Cliente não encontrado");
	}

	public ClienteDTO logar(LogarCliente dto) throws EmailOrPasswordNotValid {
		for (ClienteEntity cliente : findAll()) {
			if (cliente.getSenha().equals(dto.getSenha()) && (cliente.getEmail().equals(dto.getEmail()))) {
				cliente.setToken(gerardorId.retornaIdentificador());
//				cliente.setHoraDoToken(LocalDateTime.now());
				cliente.setHabilitado(true);// Debater sobre isso novamente;
				return clienteMapper.toDto(clienteRepository.saveAndFlush(cliente));
			}
		}
		throw new EmailOrPasswordNotValid("Email ou senha invalido");
		// Finalizado
	}

	public ClienteDTO recuperarSenha(String email) throws EmailOrPasswordNotValid {
		String emailCliente = "";
		try {
			emailCliente = clienteRepository.findByEmail(email).getEmail();
		} catch (NullPointerException erro) {
			System.out.println("Erro na busca por E-mail");
		}

		SimpleEmail emailEnviar = new SimpleEmail();
		emailEnviar.setHostName("smtp.gmail.com");
		emailEnviar.setSmtpPort(465);
		emailEnviar.setAuthenticator(new DefaultAuthenticator("fred.machado.rj@gmail.com", "1F9r8e6d?"));
		emailEnviar.setSSLOnConnect(true);

		try {
			
			emailEnviar.setFrom("fred.machado.rj@gmail.com");
			emailEnviar.setSubject("Recuperar senha");
			emailEnviar.setMsg("Testando grupo 1");
			emailEnviar.addTo(email);
			System.out.println(emailCliente);
			emailEnviar.send();
			System.out.println("Enviado ");
		} catch (EmailException e) {
			System.out.println("houve erro");
			throw new EmailOrPasswordNotValid("Email invalido");
		}

		return clienteMapper.toDto(clienteRepository.findByEmail(email));

	}

	// Essa parte é para efeitos de testes
	public ClienteDTO verCliente(String dto) {

		ClienteEntity cliente = clienteRepository.findByEmail(dto);

		return clienteMapper.toDtoPaginaDados(cliente);

	}

}
