package com.travelbooking.DTO;

import com.travelbooking.Entities.Producto;
import com.travelbooking.Entities.Puntuacion;
import com.travelbooking.Entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PuntuacionDTO {

    private Long id;
    private Double puntuacion;
    private Producto productos;
    private Usuario usuario;

    public Puntuacion toEntity() {
        Puntuacion puntuacionEn = new Puntuacion();
        puntuacionEn.setId(id);
        puntuacionEn.setPuntuacion(puntuacion);
        puntuacionEn.setProductos(productos);
        puntuacionEn.setUsuario(usuario);
        return puntuacionEn;
    }
}
