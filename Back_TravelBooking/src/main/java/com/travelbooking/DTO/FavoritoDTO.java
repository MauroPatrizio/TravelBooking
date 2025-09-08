package com.travelbooking.DTO;

import com.travelbooking.Entities.Favorito;
import com.travelbooking.Entities.Producto;
import com.travelbooking.Entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritoDTO {

    private Long id;
    private Producto producto;
    private Usuario usuario;

    public Favorito toEntity(){
        Favorito favorito = new Favorito();
        favorito.setId(this.id);
        favorito.setProducto(this.producto);
        favorito.setUsuario(this.usuario);
        return favorito;
    }
}
