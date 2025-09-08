package com.travelbooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CiudadDTO {

    private Long id;
    private String nombre;
    private PaisDTO pais;
}
