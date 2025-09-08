package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelbooking.DTO.ProductoDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="productos")
public class Producto extends Base{
    @Pattern(regexp = "^\\S.*", message = "El nombre no puede comenzar con un caracter inválido")
    @NotEmpty
    @Size(min = 3, max = 50, message = "El nombre debe ser de 3 a 50 caracteres")
    @Column(name="nombre")
    private String nombre;

    @Pattern(regexp = "^\\S.*", message = "El titulo no puede comenzar con un caracter inválido")
    @NotEmpty
    @Size(min = 3, max = 50, message = "El titulo debe ser de 3 a 50 caracteres")
    @Column(name="titulo")
    private String titulo;

    @Pattern(regexp = "^\\S.*", message = "La descripción no puede comenzar con un caracter inválido")
    @NotEmpty
    @Size(min = 10, max = 50, message = "La descripción debe ser de 10 a 50 caracteres")
    @Column(name="descripcion")
    private String descripcion;

    @Pattern(regexp = "^\\S.*", message = "El domicilio no puede comenzar con un caracter inválido")
    @NotEmpty
    @Size(min = 10, max = 50, message = "El domicilio debe ser de 10 a 50 caracteres")
    @Column(name="domicilio")
    private String domicilio;

    @NotEmpty(message = "Debe ingresar la latitud")
    @Column(name="latitud")
    private String latitud;

    @NotEmpty(message = "Debe ingresar la longitud")
    @Column(name="longitud")
    private String longitud;

    @NotNull(message = "El id de categoría no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @NotEmpty(message = "Las imágenes no pueden ser nulas")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id", nullable = false)
    private List<Imagen> imagen;

    @NotNull(message = "Las características no pueden ser nulas")
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "caracteristica_id", referencedColumnName = "id", nullable = false)
    private Caracteristica caracteristica;

    @NotNull(message = "El id de ciudad no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "ciudad_id", referencedColumnName = "id", nullable = false)
    private Ciudad ciudad;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<Reserva> reservas;

    @Column(name = "puntuacion")
    private Double puntuacion;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Favorito> favoritos;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Producto that = (Producto) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}

    public ProductoDTO toDto() {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(this.getId());
        productoDTO.setNombre(nombre);
        productoDTO.setTitulo(titulo);
        productoDTO.setDescripcion(descripcion);
        productoDTO.setLongitud(longitud);
        productoDTO.setLatitud(latitud);
        productoDTO.setDomicilio(domicilio);
        productoDTO.setCategoria(categoria);
        productoDTO.setCaracteristica(caracteristica);
        productoDTO.setCiudad(ciudad);
        productoDTO.setImagen(imagen);
        productoDTO.setReservas(reservas);
        productoDTO.setPuntuacion(puntuacion);
        return productoDTO;
    }


}
