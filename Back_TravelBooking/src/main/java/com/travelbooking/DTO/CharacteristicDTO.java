package com.travelbooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacteristicDTO {

    private Long id;
    private Boolean kitchen;
    private Integer bedrooms;
    private Boolean tv;
    private Boolean pets;
    private Boolean parking;
    private Boolean air_conditioning;
    private Boolean wifi;
    private Boolean pool;
}
