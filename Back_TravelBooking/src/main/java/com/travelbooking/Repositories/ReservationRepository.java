package com.travelbooking.Repositories;

import com.travelbooking.Entities.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation, Long>{

    //@Query("SELECT r FROM Reservation r WHERE r.product.id = ?1")
    List<Reservation> findByProductId(Long id);

    //@Query("SELECT r FROM Reservation r WHERE r.user.id = ?1")
    List<Reservation> findByUserId(Long id);

    //@Query("SELECT r FROM Reservation r WHERE r.id = ?1")
    //Reservation findOneById(Long id);
}
