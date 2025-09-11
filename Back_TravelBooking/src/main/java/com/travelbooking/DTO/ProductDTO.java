package com.travelbooking.DTO;

import com.travelbooking.Entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;
    private String name;
    private String title;
    private String description;
    private String address;
    private String latitude;
    private String longitude;
    private Category category;
    private Characteristic characteristic;
    private City city;
    private List<Image> image;
    private List<Reservation> reservations;
    private Double score;

}