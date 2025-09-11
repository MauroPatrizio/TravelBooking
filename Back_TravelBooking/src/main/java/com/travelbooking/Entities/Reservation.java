package com.travelbooking.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="reservations")
public class Reservation extends Base {

    @NotNull
    @Column(name="check_in_time")
    private LocalTime checkInTime;

    @NotNull
    @Column(name="check_in_date")
    private LocalDate checkInDate;

    @NotNull
    @Column(name="check_out_date")
    private LocalDate checkOutDate;

    @NotNull(message = "The user ID can't be null")
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @NotNull(message = "The product id can't be null")
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;

    @Column(name = "reservation_name")
    private String reservationName;

    @Column(name = "reservation_surname")
    private String reservationSurname;

    @Email(message = "The email must be valid")
    @Column(name = "reservation_email")
    private String reservationEmail;

    @Column(name = "reservation_phone")
    private String reservationPhone;

    @Column(name = "comments")
    private String comments;

    @Column(name = "vaccination")
    private Boolean vaccination;

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(this.getId(), that.getId());
    }

    @Override
    public int hashCode() { return Objects.hash(getId()); }
}
