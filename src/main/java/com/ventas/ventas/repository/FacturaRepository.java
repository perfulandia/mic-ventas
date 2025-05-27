package com.ventas.ventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ventas.ventas.model.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{
    
}
