package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.travelbooking.DTO.ReservaDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="reservas")
public class Reserva extends Base{

    @NotNull
    @Column(name="hora_inicial")
    private LocalTime horaInicial;

    @NotNull
    @Column(name="fecha_inicial")
    private LocalDate fechaInicial;

    @NotNull
    @Column(name="fecha_final")
    private LocalDate fechaFinal;

    @NotNull(message = "El id de usuario no puede ser nulo")
    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuario usuario;

    @NotNull(message = "El id de producto no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "producto_id")
    @JsonIgnore
    private Producto producto;

    @Column(name = "nombre_reserva")
    private String nombreReserva;

    @Column(name = "apellido_reserva")
    private String apellidoReserva;

    @Email(message = "El email debe ser válido")
    @Column(name = "email_reserva")
    private String emailReserva;

    @Column(name = "telefono_reserva")
    private String telefonoReserva;

    @Column(name = "comentarios")
    private String comentarios;

    @Column(name = "vacunacion")
    private Boolean vacunacion;

    //Equals y HashCode para comparar objetos por id
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Reserva that = (Reserva) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId());}

    public ReservaDTO toDTO() {
        ReservaDTO reservaDTO = new ReservaDTO();
        reservaDTO.setId(getId());
        reservaDTO.setHoraInicial(horaInicial);
        reservaDTO.setFechaInicial(fechaInicial);
        reservaDTO.setFechaFinal(fechaFinal);
        reservaDTO.setUsuario(usuario);
        reservaDTO.setProducto(producto);
        reservaDTO.setNombreReserva(nombreReserva);
        reservaDTO.setApellidoReserva(apellidoReserva);
        reservaDTO.setEmailReserva(emailReserva);
        reservaDTO.setTelefonoReserva(telefonoReserva);
        reservaDTO.setComentarios(comentarios);
        reservaDTO.setVacunacion(vacunacion);
        return reservaDTO;
    }
}
