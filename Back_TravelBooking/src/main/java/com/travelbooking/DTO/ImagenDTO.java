package com.travelbooking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImagenDTO {

    private Long id;
    private String title;
    private String url;
}
