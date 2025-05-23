package com.ventas.ventas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ventas.ventas.dto.ProductoDto;
import com.ventas.ventas.model.Factura;
import com.ventas.ventas.repository.FacturaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Factura> findAll(){
        return facturaRepository.findAll();
    }

    public ProductoDto getProductos(List<Long> ids){
        String API_URL = "http://localhost:8080/api/v1/inventario";
        ResponseEntity<ProductoDto> response = restTemplate.getForEntity(API_URL, ProductoDto.class);

        ProductoDto producto = response.getBody();
        return producto;
    }
}
