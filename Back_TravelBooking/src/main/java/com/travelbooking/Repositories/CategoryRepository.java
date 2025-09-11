package com.travelbooking.Repositories;

import com.travelbooking.Entities.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {
}
