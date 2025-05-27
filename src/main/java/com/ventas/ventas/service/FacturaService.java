package com.ventas.ventas.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.ventas.ventas.dto.ProductoDto;
import com.ventas.ventas.model.Factura;
import com.ventas.ventas.model.ProductoCantidad;
import com.ventas.ventas.repository.FacturaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private RestTemplate restTemplate;
    private final String API_URL = "http://localhost:9000/api/v1/inventario";

    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    public List<ProductoDto> getProductosPedido(List<Long> ids) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(API_URL + "/by-id/").queryParam("ids", ids);

        ResponseEntity<List<ProductoDto>> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<ProductoDto>>() {
                });

        return response.getBody();
    }

    public Factura createFactura(Factura factura) {
        Factura nuevaFactura = factura;
        Long subtotal = 0L; // la L es para castear el 0 a Long

        List<Long> ids = new ArrayList<>();
        List<Long> cantidad = new ArrayList<>();

        for (ProductoCantidad prod : factura.getProductos()) { // separamos el id de cada producto para buscarlo de la
                                                               // API inventario
            ids.add(prod.getIdProducto());
            cantidad.add(prod.getCantidad());

        }

        List<ProductoDto> productosApi = this.getProductosPedido(ids); // lista de productos del inv

        for (ProductoDto prod : productosApi) { // sumar el valor de cada producto al subtotal
            int i = 0;
            subtotal += (prod.getPrecio() * cantidad.get(i));

            i++;
        }

        // asignar valores
        nuevaFactura.setSubtotal(subtotal);
        nuevaFactura.setTotalIva(Math.round(subtotal * 0.19));
        nuevaFactura.setTotal(Math.round(subtotal * 1.19));

        facturaRepository.save(nuevaFactura);
        return nuevaFactura;
    }

}
