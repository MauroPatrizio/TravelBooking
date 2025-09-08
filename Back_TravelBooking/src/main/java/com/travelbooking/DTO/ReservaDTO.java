package com.travelbooking.DTO;

import com.travelbooking.Entities.Producto;
import com.travelbooking.Entities.Reserva;
import com.travelbooking.Entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDTO {

    private Long id;
    private LocalTime horaInicial;
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private Usuario usuario;
    private Producto producto;
    private String nombreReserva;
    private String apellidoReserva;
    private String emailReserva;
    private String telefonoReserva;
    private String comentarios;
    private Boolean vacunacion;

    public Reserva toEntity() {
        Reserva reserva = new Reserva();
        reserva.setId(id);
        reserva.setHoraInicial(horaInicial);
        reserva.setFechaInicial(fechaInicial);
        reserva.setFechaFinal(fechaFinal);
        reserva.setUsuario(usuario);
        reserva.setProducto(producto);
        reserva.setNombreReserva(nombreReserva);
        reserva.setApellidoReserva(apellidoReserva);
        reserva.setEmailReserva(emailReserva);
        reserva.setTelefonoReserva(telefonoReserva);
        reserva.setComentarios(comentarios);
        reserva.setVacunacion(vacunacion);
        return reserva;
    }
}
