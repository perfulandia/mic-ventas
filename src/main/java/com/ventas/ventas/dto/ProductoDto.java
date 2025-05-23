package com.ventas.ventas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ProductoDto {
    
    private Long id;
    
    private String nombre;

    private Double precio;

    private int stock;

    private String marca;
}
