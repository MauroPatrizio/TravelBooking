package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelbooking.DTO.PuntuacionDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Categorias")
public class Puntuacion extends Base{

    @NotNull(message = "La puntuación no puede estar vacía")
    @Min(value = 1, message = "La puntuación debe ser de al menos 1")
    @Max(value = 5, message = "la puntuación debe ser 5 como máximo")
    @Column(name="puntuacion")
    private Double puntuacion;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Producto productos;

    @NotNull(message = "El id de usuario no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.MERGE)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Puntuacion that = (Puntuacion) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}

    public PuntuacionDTO toDto() {
        PuntuacionDTO puntuacionDTO = new PuntuacionDTO();
        puntuacionDTO.setId(getId());
        puntuacionDTO.setPuntuacion(puntuacion);
        puntuacionDTO.setProductos(productos);
        puntuacionDTO.setUsuario(usuario);
        return puntuacionDTO;
    }

}
