package com.ventas.ventas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ProductoCantidad {

    @Column(name = "id_producto")
    private Long idProducto;
    
    private Long cantidad;
}
