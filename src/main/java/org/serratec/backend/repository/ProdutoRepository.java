package org.serratec.backend.repository;

import org.serratec.backend.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long>{
	
	@Query("FROM ProdutoEntity WHERE nome LIKE ?1 ")
	ProdutoEntity getByName(@Param("produto")String produto);

}
