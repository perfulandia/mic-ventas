package com.ventas.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    // obtener factura segun su id
    @GetMapping("/id/{id}")
    public ResponseEntity<Factura> findProducto(@PathVariable Long id){

        if (facturaService.existsById(id)){
            return new ResponseEntity<>(facturaService.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    // obtener una lista de facturas pasando sus id's
    @GetMapping("/by-id/")
    public ResponseEntity<List<Factura>> getProductosById(@RequestParam List<Long> ids){
        return new ResponseEntity<>(facturaService.findAllById(ids), HttpStatus.OK);
    }

    // actualizar factura con su id
    @PutMapping("/id/{id}")
    public ResponseEntity<Factura> updateProducto(@PathVariable Long id, @RequestBody Factura factura){

        if (facturaService.existsById(id)){
            return new ResponseEntity<>(facturaService.update(id, factura), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // eliminar factura con su id
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id){
        if (facturaService.existsById(id)){
            facturaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
