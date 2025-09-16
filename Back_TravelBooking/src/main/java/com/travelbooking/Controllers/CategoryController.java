package com.travelbooking.Controllers;

import com.travelbooking.DTO.CategoryDTO;
import com.travelbooking.Entities.Category;
import com.travelbooking.Repositories.CategoryRepository;
import com.travelbooking.Services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, Long, CategoryRepository, CategoryService> {

    private final CategoryService categoryService;

    public CategoryController(CategoryService service) {
        super(service);
        this.categoryService = service;
    }

    @GetMapping("/dto")
    public ResponseEntity<Set<CategoryDTO>> findAllDTO() {
        try {
            return ResponseEntity.ok(categoryService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<CategoryDTO> findDTOById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoryService.findDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dto")
    public ResponseEntity<CategoryDTO> createFromDTO(@RequestBody CategoryDTO dto) {
        try {
            return ResponseEntity.ok(categoryService.createFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/dto")
    public ResponseEntity<CategoryDTO> updateFromDTO(@RequestBody CategoryDTO dto) {
        try {
            return ResponseEntity.ok(categoryService.updateFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
