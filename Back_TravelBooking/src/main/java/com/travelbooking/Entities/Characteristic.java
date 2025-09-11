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
@Table(name="characteristics")
public class Characteristic extends Base{

    @NonNull
    @Column(name="kitchen")
    private Boolean kitchen;

    @NonNull
    @Column(name="bedrooms")
    private Integer bedrooms;

    @NonNull
    @Column(name="tv")
    private Boolean tv;

    @NonNull
    @Column(name="pets")
    private Boolean pets;

    @NonNull
    @Column(name="parking")
    private Boolean parking;

    @NonNull
    @Column(name="air_conditioning")
    private Boolean air_conditioning;

    @NonNull
    @Column(name="wifi")
    private Boolean wifi;

    @NonNull
    @Column(name="pool")
    private Boolean pool;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Characteristic that = (Characteristic) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}
}
