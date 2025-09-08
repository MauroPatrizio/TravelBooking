package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pais")
public class Pais extends Base {

    @Column(name="nombre")
    private String nombre;

    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Ciudad> ciudad;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Pais that = (Pais) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}
}
