package com.ventas.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.ventas.model.Factura;
import com.ventas.ventas.repository.FacturaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> findAll(){
        return facturaRepository.findAll();
    }
}
