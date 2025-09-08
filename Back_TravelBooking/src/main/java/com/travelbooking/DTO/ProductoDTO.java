package com.travelbooking.DTO;

import com.travelbooking.Entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String titulo;
    private String descripcion;
    private String domicilio;
    private String latitud;
    private String longitud;
    private Categoria categoria;
    private Caracteristica caracteristica;
    private Ciudad ciudad;
    private List<Imagen> imagen;
    private List<Reserva> reservas;
    private Double puntuacion;

    public Producto toEntity() {
        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setTitulo(titulo);
        producto.setDescripcion(descripcion);
        producto.setLongitud(longitud);
        producto.setLatitud(latitud);
        producto.setDomicilio(domicilio);
        producto.setCategoria(categoria);
        producto.setCaracteristica(caracteristica);
        producto.setCiudad(ciudad);
        producto.setImagen(imagen);
        producto.setReservas(reservas);
        producto.setPuntuacion(puntuacion);
        return producto;
    }

}