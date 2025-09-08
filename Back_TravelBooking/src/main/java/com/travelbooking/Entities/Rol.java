package com.travelbooking.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Rol extends Base{

    @Pattern(regexp = "^\\S.*",message = "El nombre no puede comenzar con un carácter no válido")
    @NotEmpty(message = "El rol tiene que contener un nombre")
    @Size(min = 1,max = 50,message = "El nombre del rol no puede contener menos de un carácter y más de cincuenta")
    @Column(name = "nombre")
    private String name;
}
