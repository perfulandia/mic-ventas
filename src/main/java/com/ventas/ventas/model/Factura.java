package com.ventas.ventas.model;

import java.time.LocalDate;
import java.util.List;

import com.ventas.ventas.dto.ProductoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String rutEmpresa;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(length = 15, nullable = false)
    private Double subtotal;

    @Column(length = 15, nullable = false)
    private Double total;

    @Column(length = 15, nullable = false)
    private Double totalIva;

    @Column(nullable = false)
    private List<Par<ProductoDto, Long>> detalle;

}
