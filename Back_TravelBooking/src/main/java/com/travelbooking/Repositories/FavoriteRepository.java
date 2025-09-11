package com.travelbooking.Repositories;

import com.travelbooking.Entities.Favorite;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends BaseRepository<Favorite, Long> {
}
