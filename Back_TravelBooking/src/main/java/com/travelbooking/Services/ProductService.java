package com.travelbooking.Services;

import com.travelbooking.DTO.ProductDTO;
import com.travelbooking.Entities.Product;
import com.travelbooking.Repositories.ProductRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService extends BaseService<Product, Long, ProductRepository> {

    public ProductService(ProductRepository repository) {
        super(repository);
    }

    private ProductDTO toDTO(Product entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getAddress(),
                entity.getLatitude(),
                entity.getLongitude(),
                entity.getCategory(),
                entity.getCharacteristic(),
                entity.getCity(),
                entity.getImage(),
                entity.getReservations(),
                entity.getScore() != null ? entity.getScore() : 0.0
        );
    }

    private Product toEntity(ProductDTO dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setAddress(dto.getAddress());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setCategory(dto.getCategory());
        entity.setCharacteristic(dto.getCharacteristic());
        entity.setCity(dto.getCity());
        entity.setImage(dto.getImage());
        entity.setReservations(dto.getReservations());
        entity.setScore(dto.getScore() != null ? dto.getScore() : 0.0);
        entity.setActive(true);
        return entity;
    }

    public ProductDTO findDTOById(Long id) throws Exception {
        Optional<Product> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("Product not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public ProductDTO createFromDTO(ProductDTO dto) throws Exception {
        Product entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public ProductDTO updateFromDTO(ProductDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("Product not found with id: " + dto.getId());
        }
        Product entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public List<ProductDTO> findAllDTO() throws Exception {
        return repository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Set<ProductDTO> findByCity(String cityName) {
        return repository.findByActiveTrue()
                .stream()
                .filter(product -> product.getCity() != null && product.getCity().getName().equals(cityName))
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public Set<ProductDTO> findRandomProducts() {
        return repository.findRandomProduct()
                .stream()
                .map(product -> {
                    if (product.getScore() == null) product.setScore(0.0);
                    return toDTO(product);
                })
                .collect(Collectors.toSet());
    }

    public ProductDTO findOneById(Long id) throws Exception {
        Product product = repository.findOneById(id);
        if (product == null) {
            throw new Exception("Product not found with id: " + id);
        }
        if (product.getScore() == null) product.setScore(0.0);
        return toDTO(product);
    }

    public Set<ProductDTO> findByCityAndBetweenDates(String cityName, LocalDate startDate, LocalDate endDate) {
        return repository.findByCityAndBetweenDates(cityName, startDate, endDate)
                .stream()
                .map(product -> {
                    if (product.getScore() == null) product.setScore(0.0);
                    return toDTO(product);
                })
                .collect(Collectors.toSet());
    }
}
