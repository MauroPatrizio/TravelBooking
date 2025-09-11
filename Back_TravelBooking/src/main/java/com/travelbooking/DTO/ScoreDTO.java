package com.travelbooking.DTO;

import com.travelbooking.Entities.Product;
import com.travelbooking.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {
    private Long id;
    private Double score;
    private Product product;
    private User user;
}
