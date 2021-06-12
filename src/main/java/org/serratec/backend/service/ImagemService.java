package org.serratec.backend.service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.serratec.backend.entity.ImagemEntity;
import org.serratec.backend.entity.ProdutoEntity;
import org.serratec.backend.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImagemService {
	
	@Autowired
	ImagemRepository imagemRepository;
	
	@Transactional
	public ImagemEntity create(ProdutoEntity produto, MultipartFile file) throws IOException{
		ImagemEntity imagem = new ImagemEntity();
		imagem.setProduto(produto);
		imagem.setCodigoDoProduto(produto.getCodigoProduto());
		imagem.setData(file.getBytes());
		imagem.setMimetype(file.getContentType());
		imagem.setNome("Imagem");
		return imagemRepository.save(imagem); 
	}
	
	@Transactional
	public ImagemEntity getImagem(String id) {
		ImagemEntity imagem = imagemRepository.findByCodigoProduto(id);
		return imagem;
	}

}
