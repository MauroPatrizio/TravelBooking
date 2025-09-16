package com.travelbooking.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.Set;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {

    Set<T> findByActiveTrue();

}
