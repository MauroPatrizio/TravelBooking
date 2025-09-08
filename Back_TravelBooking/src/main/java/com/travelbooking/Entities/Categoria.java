package com.travelbooking.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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
@Table(name="categorias")
public class Categoria  extends Base {

    @Pattern(regexp = "^\\S.*", message = "No puede empezar con un caracter inválido")
    @NotEmpty(message = "La categoría debe contener un título")
    @Size(min = 10, max = 30, message = "El título debe ser de 10 a 30 caracteres")
    @Column(name="titulo")
    private String titulo;

    @Pattern(regexp = "^\\S.*", message = "No puede empezar con un caracter inválido")
    @NotEmpty(message = "La categoría debe contener una descripción")
    @Size(min = 10, max = 300, message = "La descripción debe ser de 10 a 300 caracteres")
    @Column(name="descripcion")
    private String descripcion;

    @Column(name="imagen")
    private String imagen;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Categoria that = (Categoria) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}


}
