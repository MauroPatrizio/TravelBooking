package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelbooking.DTO.UsuarioDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuarios")
public class Usuario extends Base{

    @Pattern(regexp = "^\\S.*",message = "El nombre no puede comenzar con un carácter no válido")
    @NotEmpty(message = "El usuario tiene que contener un nombre")
    @Size(min = 1,max = 50,message = "El nombre del usuario no puede contener menos de un carácter y más de cincuenta")
    @Column(name = "nombre")
    private String nombre;

    @Pattern(regexp = "^\\S.*",message = "El apellido no puede comenzar con un carácter no válido")
    @NotEmpty(message = "El usuario tiene que contener un apellido")
    @Size(min = 1,max = 50,message = "El apellido del usuario no puede contener menos de un carácter y más de cincuenta")
    @Column(name = "apellido")
    private String apellido;

    @Pattern(regexp = "^\\S.*",message = "El email no puede comenzar con un carácter no válido")
    @Email(message = "El email debe ser válido")
    @NotEmpty(message = "El usuario tiene que contener un email")
    @Size(min = 1,max = 50,message = "El email del usuario no puede contener menos de un carácter y más de cincuenta")
    @Column(name = "email", unique = true)
    private String email;

    @NotEmpty(message = "El usuario tiene que contener una contraseña")
    @Column(name ="password", length = 300)
    private String password;

    @Pattern(regexp ="^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",message = "El telefono debe ser cargado de la forma '+xx (xxx) xxx-xxxx'")
    @NotEmpty(message = "El usuario tiene que contener un número de telefono")
    @Size(min = 1,max = 50,message = "El telefono del usuario no puede contener menos de un carácter y más de cincuenta")
    @Column(name = "telefono")
    private String telefono;

    @NotNull(message = "El usuario tiene que tener un rol determinado")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id",nullable = false)
    private Rol roles;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<Puntuacion> puntuacion;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private Set<Favorito> favoritos;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Usuario that = (Usuario) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}

    public UsuarioDTO toDto() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(getId());
        usuarioDTO.setNombre(nombre);
        usuarioDTO.setApellido(apellido);
        usuarioDTO.setEmail(email);
        usuarioDTO.setPassword(password);
        usuarioDTO.setTelefono(telefono);
        usuarioDTO.setRoles(roles);
        usuarioDTO.setPuntuacion(puntuacion);
        return usuarioDTO;
    }
}
