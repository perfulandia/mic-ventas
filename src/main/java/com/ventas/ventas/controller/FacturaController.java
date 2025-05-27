package com.ventas.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventas.ventas.model.Factura;
import com.ventas.ventas.service.FacturaService;

@RequestMapping("/api/v1/factura")
@RestController

public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> getFacturas() {
        List<Factura> facturas = facturaService.findAll();

        if (!facturas.isEmpty()) {
            return new ResponseEntity<>(facturas, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody Factura factura) {
        /* Espera una factura con numPedido, rutEmpresa, fecha, "productos"

        Formato:
            {
            "numPedido": 1,
            "rutEmpresa": "12345678-9",
            "fecha": "yyyy-mm-dd",
            "productos": [{"idProducto": 1,
                            "cantidad": 1}]
            } 
        */

        if (factura != null && factura.isValid()) {
            return new ResponseEntity<>(facturaService.createFactura(factura), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}
