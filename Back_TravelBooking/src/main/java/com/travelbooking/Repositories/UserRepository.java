package com.travelbooking.Repositories;

import com.travelbooking.Entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long>{

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
