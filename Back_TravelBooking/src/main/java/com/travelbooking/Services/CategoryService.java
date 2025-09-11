package com.travelbooking.Services;

import com.travelbooking.DTO.CategoryDTO;
import com.travelbooking.Entities.Category;
import com.travelbooking.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService extends BaseService<Category, Long, CategoryRepository> {

    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    private CategoryDTO toDTO(Category entity) {
        return new CategoryDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage()
        );
    }

    private Category toEntity(CategoryDTO dto) {
        Category entity = new Category();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setImage(dto.getImage());
        entity.setActive(true);
        return entity;
    }

    public CategoryDTO findDTOById(Long id) throws Exception {
        Optional<Category> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("Category not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public CategoryDTO createFromDTO(CategoryDTO dto) throws Exception {
        Category entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public CategoryDTO updateFromDTO(CategoryDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("Category not found with id: " + dto.getId());
        }
        Category entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public List<CategoryDTO> findAllDTO() throws Exception {
        return repository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
