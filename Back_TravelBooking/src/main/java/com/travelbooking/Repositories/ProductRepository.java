package com.travelbooking.Repositories;

import com.travelbooking.Entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    @Query(value = "SELECT u FROM products u ORDER BY RAND()", nativeQuery = true)
    List<Product> findRandomProduct();

    @Query("SELECT u FROM Product u WHERE u.id = ?1")
    Product findOneById(Long id);

    @Query(value = "{call findByCityAndBetweenDates_procedure(?1, ?2, ?3)}", nativeQuery = true)
    List<Product> findByCityAndBetweenDates(String ciudadName, LocalDate fechaInicio, LocalDate fechaFin);

    @Procedure(procedureName = "findAllProductByScore")
    List<Product> findAllProductByScore();
}
