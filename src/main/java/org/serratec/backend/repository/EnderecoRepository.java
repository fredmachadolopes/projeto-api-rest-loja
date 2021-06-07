package org.serratec.backend.repository;

import java.awt.List;

import org.serratec.backend.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long>{
	
	@Query("FROM EnderecoEntity WHERE rua LIKE  ?1 AND numero LiKE ?2 AND complemento LIKE ?3")
	public EnderecoEntity findEndereco(@Param("nome") String nome, @Param("numcasa") String numCasa, @Param("num") String num);

}
