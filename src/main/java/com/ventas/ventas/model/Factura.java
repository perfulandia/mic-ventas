package com.ventas.ventas.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    @Column(nullable = false)
    private Long numPedido;

    @Column(length = 20, nullable = false)
    private String rutEmpresa;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(length = 15, nullable = false)
    private Long subtotal;

    @Column(length = 15, nullable = false)
    private Long total;

    @Column(length = 15, nullable = false)
    private Long totalIva;

    @ElementCollection
    @CollectionTable(name = "producto_cantidad", joinColumns = @JoinColumn(name = "id"))
    @Column(nullable = false)
    private List<ProductoCantidad> productos;

    public boolean isValid() {
        if (this.numPedido != null
                && this.rutEmpresa != null
                && this.fecha != null
                && productos != null) {
            return true;
        }
        return false;
    }
}
