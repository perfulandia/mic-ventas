package com.ventas.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ventas.ventas.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{

    
} 