package com.travelbooking.DTO;

import com.travelbooking.Entities.Favorite;
import com.travelbooking.Entities.Product;
import com.travelbooking.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDTO {

    private Long id;
    private Product product;
    private User user;

    public Favorite toEntity(){
        Favorite favorite = new Favorite();
        favorite.setId(this.id);
        favorite.setProduct(this.product);
        favorite.setUser(this.user);
        return favorite;
    }
}
