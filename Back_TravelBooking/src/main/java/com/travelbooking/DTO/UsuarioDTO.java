package com.travelbooking.DTO;


import com.travelbooking.Entities.Puntuacion;
import com.travelbooking.Entities.Rol;
import com.travelbooking.Entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;
    private Rol roles;
    private List<Puntuacion> puntuacion;

     public Usuario toEntity() {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setTelefono(telefono);
        usuario.setRoles(roles);
        usuario.setPuntuacion(puntuacion);
        return usuario;
    }
}
