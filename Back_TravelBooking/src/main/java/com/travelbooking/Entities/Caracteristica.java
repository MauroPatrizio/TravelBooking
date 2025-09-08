package com.travelbooking.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="caracteristicas")
public class Caracteristica  extends Base{

    @NonNull
    @Column(name="cocina")
    private Boolean cocina;

    @NonNull
    @Column(name="habitaciones")
    private Integer habitaciones;

    @NonNull
    @Column(name="televisor")
    private Boolean tv;

    @NonNull
    @Column(name="mascotas")
    private Boolean mascotas;

    @NonNull
    @Column(name="estacionamiento")
    private String estacionamiento;

    @NonNull
    @Column(name="aire_acondicionado")
    private Boolean aireAcondicionado;

    @NonNull
    @Column(name="wifi")
    private Boolean wifi;

    @NonNull
    @Column(name="pileta")
    private Boolean pileta;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Caracteristica that = (Caracteristica) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}
}
