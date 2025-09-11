package com.travelbooking.Services;

import com.travelbooking.Entities.Base;
import com.travelbooking.Repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class BaseService<T extends Base, ID, Repo extends BaseRepository<T, ID>> {

    protected final Repo repository;

    @Autowired
    public BaseService(Repo repository) {
        this.repository = repository;
    }

    public List<T> findAll() throws Exception {
        try {
            return repository.findByActiveTrue();
        } catch (Exception e) {
            throw new Exception("Error while fetching all entities: " + e.getMessage(), e);
        }
    }

    public Optional<T> findById(ID id) throws Exception {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            throw new Exception("Error while fetching by id: " + e.getMessage(), e);
        }
    }

    public T create(T entity) throws Exception {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new Exception("Error while creating entity: " + e.getMessage(), e);
        }
    }

    public T update(ID id, T entity) throws Exception {
        try {
            if (repository.existsById(id)) {
                entity.setId((Long) id); // keep same ID
                return repository.save(entity);
            } else {
                throw new Exception("Entity not found with id: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error while updating entity: " + e.getMessage(), e);
        }
    }

    public void deleteById(ID id) throws Exception {
        try {
            Optional<T> entityOpt = repository.findById(id);
            if (entityOpt.isPresent()) {
                T entity = entityOpt.get();
                entity.setActive(false);
                repository.save(entity);
            } else {
                throw new Exception("Entity not found with id: " + id);
            }
        } catch (Exception e) {
            throw new Exception("Error while deleting entity (soft delete): " + e.getMessage(), e);
        }
    }
}