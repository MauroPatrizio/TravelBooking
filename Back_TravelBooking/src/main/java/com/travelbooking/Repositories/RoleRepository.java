package com.travelbooking.Repositories;

import com.travelbooking.Entities.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role, Long>{

    Optional<Role> findByName(String name);
}
