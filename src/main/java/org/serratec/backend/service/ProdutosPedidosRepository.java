package org.serratec.backend.service;

import org.serratec.backend.entity.ProdutosPedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosPedidosRepository  extends JpaRepository<ProdutosPedidosEntity, Long>{

}
