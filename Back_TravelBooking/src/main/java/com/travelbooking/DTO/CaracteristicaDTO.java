package com.travelbooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristicaDTO {

    private Long id;
    private Boolean cocina;
    private Integer habitaciones;
    private Boolean tv;
    private Boolean mascotas;
    private String estacionamiento;
    private Boolean aireAcondicionado;
    private Boolean wifi;
    private Boolean pileta;
}
