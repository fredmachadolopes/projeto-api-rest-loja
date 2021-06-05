package org.serratec.backend.repository;

import org.serratec.backend.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
//	@Query("FROM ClienteEntiy  WHERE email = :email AND senha = :senha ")
//	ClienteEntity findCustom(@Param("email") String email, @Param("senha") String senha);
	
//	@Query("FROM ClienteEntiy ")
//	public void findCustom();
}
