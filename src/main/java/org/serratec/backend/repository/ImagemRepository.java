package org.serratec.backend.repository;

import org.serratec.backend.entity.ImagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImagemRepository extends JpaRepository<ImagemEntity, Long> {
	
	@Query("From ImagemEntity WHERE codigo_do_produto = ?1")
	ImagemEntity findByCodigoProduto(@Param("id")String id);



}
