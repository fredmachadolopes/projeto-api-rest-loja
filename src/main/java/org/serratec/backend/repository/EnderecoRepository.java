package org.serratec.backend.repository;



import java.util.List;

import org.serratec.backend.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long>{
	
	@Query("FROM EnderecoEntity where cliente_id = ?1")
	List<EnderecoEntity> findAllByIdCliente(@Param ("cliente_id") Long id);
	@Query("FROM EnderecoEntity where identificador = ?1")
	public EnderecoEntity findByIdentificador(@Param("identificador") String identificador);

}
