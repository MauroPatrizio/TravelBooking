package com.travelbooking.Repositories;

import com.travelbooking.Entities.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends BaseRepository<Country, Long> {
}
