package com.travelbooking.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
@Table(name="imagenes")
public class Imagen extends Base{

    @NotEmpty(message = "La imágen debe tener un título")
    @Size(min = 3, max = 30, message = "El título de la imágen debe contener entre 3 y 30 caracteres")
    @Column(name="titulo")
    private String titulo;

    @NotEmpty(message = "La imágen debe tener una url")
    @Size(min = 3, max = 500, message = "El título de la imágen debe contener entre 3 y 500 caracteres")
    @Column(name="url")
    private String url;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Imagen that = (Imagen) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}
}
