package com.travelbooking.Controllers;

import com.travelbooking.DTO.ProductDTO;
import com.travelbooking.Entities.Product;
import com.travelbooking.Repositories.ProductRepository;
import com.travelbooking.Services.ProductService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductController extends BaseController<Product, Long, ProductRepository, ProductService> {

    private final ProductService productService;

    public ProductController(ProductService service) {
        super(service);
        this.productService = service;
    }

    @GetMapping("/dto")
    public ResponseEntity<Set<ProductDTO>> findAllDTO() {
        try {
            return ResponseEntity.ok(productService.findAllDTO());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<ProductDTO> findDTOById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productService.findDTOById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/dto")
    public ResponseEntity<ProductDTO> createFromDTO(@RequestBody ProductDTO dto) {
        try {
            return ResponseEntity.ok(productService.createFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/dto")
    public ResponseEntity<ProductDTO> updateFromDTO(@RequestBody ProductDTO dto) {
        try {
            return ResponseEntity.ok(productService.updateFromDTO(dto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/random")
    public ResponseEntity<Set<ProductDTO>> findRandomProducts() {
        return ResponseEntity.ok(productService.findRandomProducts());
    }

    @GetMapping("/city/{cityName}")
    public ResponseEntity<Set<ProductDTO>> findByCity(@PathVariable("cityName") String cityName) {
        return ResponseEntity.ok(productService.findByCity(cityName));
    }

    @GetMapping("/city/{cityName}/{startDate}/{endDate}")
    public ResponseEntity<Set<ProductDTO>> findByCityAndBetweenDates(
            @PathVariable("cityName") String cityName,
            @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        Set<ProductDTO> products = productService.findByCityAndBetweenDates(cityName, startDate, endDate);
        if (products.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(products);
    }
}
