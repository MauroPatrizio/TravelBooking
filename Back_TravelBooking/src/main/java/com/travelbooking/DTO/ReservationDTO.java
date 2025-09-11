package com.travelbooking.DTO;

import com.travelbooking.Entities.Product;
import com.travelbooking.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Long id;
    private LocalTime checkInTime;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private User user;
    private Product product;
    private String reservationName;
    private String reservationSurname;
    private String reservationEmail;
    private String reservationPhone;
    private String comments;
    private Boolean vaccination;
}
