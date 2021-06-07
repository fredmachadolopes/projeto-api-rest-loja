package org.serratec.backend.repository;

import org.serratec.backend.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

	@Query("FROM ClienteEntity cliente where cliente.email like :email")
	ClienteEntity findByEmail(@Param("email") String email);
	
//	@Query("DELETE FROM ClienteEntity  where email = :email")
	void deleteByEmail(String email);
	
//	@Query("FROM ClienteEntity order by email")
//	ClienteEntity findCustom();
}
//@Query("FROM BookEntity where categoria_id =:id")
//List<BookEntity> findCuston(@Param("id")Long id);