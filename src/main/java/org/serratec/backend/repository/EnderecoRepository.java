package org.serratec.backend.repository;



import java.util.List;

import org.serratec.backend.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long>{
	
	@Query("FROM EnderecoEntity WHERE rua LIKE  ?1 AND numero LiKE ?2 AND complemento LIKE ?3")
	public EnderecoEntity findEndereco(@Param("nome") String nome, @Param("numcasa") String numCasa, @Param("num") String num);
	
	@Query("FROM EnderecoEntity where cliente_id = ?1")
	List<EnderecoEntity> findAllByIdCliente(@Param ("cliente_id") Long id);

}
