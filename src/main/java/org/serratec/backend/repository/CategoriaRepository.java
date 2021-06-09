package org.serratec.backend.repository;

import org.serratec.backend.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long>{
	
	@Query("FROM CategoriaEntity where identificador = ?1")
	CategoriaEntity getByIdentificador(@Param ("valor")String valor);

	

}
