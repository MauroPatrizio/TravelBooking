package com.travelbooking.Controllers;

import com.travelbooking.Entities.Base;
import com.travelbooking.Repositories.BaseRepository;
import com.travelbooking.Services.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
public abstract class BaseController<T extends Base, ID, Repo extends BaseRepository<T, ID>, Service extends BaseService<T, ID, Repo>> {

    protected final Service service;

    public BaseController(Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<T>> findAll() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> findById(@PathVariable ID id) {
        try {
            Optional<T> result = service.findById(id);
            return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        try {
            return ResponseEntity.ok(service.create(entity));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        try {
            if (service.findById(id).isPresent()) {
                return ResponseEntity.ok(service.update(id, entity));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable ID id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
