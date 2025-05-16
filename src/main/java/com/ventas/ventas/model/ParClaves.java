package com.ventas.ventas.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParClaves<X, Y> {

    private X idProducto;
    private Y cantidad;

    // public ObjetoPedido(X idProducto, Y cantidad){
    //     this.idProducto = idProducto;
    //     this.cantidad = cantidad;
    // }
    
    // public X getIdProducto() {
    //     return idProducto;
    // }

    // public Y getCantidad() {
    //     return cantidad;
    // }

    // @Override
    // public String toString() {
    //     return idProducto + ", " + cantidad;
    // }
}
