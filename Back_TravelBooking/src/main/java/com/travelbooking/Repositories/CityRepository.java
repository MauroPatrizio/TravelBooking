package com.travelbooking.Repositories;

import com.travelbooking.Entities.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends BaseRepository<City, Long> {
}
