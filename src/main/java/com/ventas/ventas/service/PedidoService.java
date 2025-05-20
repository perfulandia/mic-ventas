package com.ventas.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.ventas.model.Pedido;
import com.ventas.ventas.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }
}
