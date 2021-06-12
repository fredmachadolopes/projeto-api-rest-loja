package org.serratec.backend.repository;

import java.util.List;

import org.serratec.backend.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long>{

	@Query("FROM PedidoEntity WHERE cliente = ?1  ")
	public List<PedidoEntity> getByIdCliente(@Param ("cliente") Long cliente);
	
	@Query("FROM PedidoEntity WHERE numero_pedido = ?1  ")
	public PedidoEntity getByNumeroPedido(@Param ("numero") Long numero);
}
