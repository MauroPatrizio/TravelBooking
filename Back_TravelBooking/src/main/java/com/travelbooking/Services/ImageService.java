package com.travelbooking.Services;

import com.travelbooking.DTO.ImageDTO;
import com.travelbooking.Entities.Image;
import com.travelbooking.Repositories.ImageRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ImageService extends BaseService<Image, Long, ImageRepository> {

    public ImageService(ImageRepository repository) {
        super(repository);
    }

    private ImageDTO toDTO(Image entity) {
        return new ImageDTO(
                entity.getId(),
                entity.getTitle(),
                entity.getUrl()
        );
    }

    private Image toEntity(ImageDTO dto) {
        Image entity = new Image();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setUrl(dto.getUrl());
        entity.setActive(true);
        return entity;
    }

    public ImageDTO findDTOById(Long id) throws Exception {
        Optional<Image> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("Image not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public ImageDTO createFromDTO(ImageDTO dto) throws Exception {
        Image entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public ImageDTO updateFromDTO(ImageDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("Image not found with id: " + dto.getId());
        }
        Image entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public List<ImageDTO> findAllDTO() throws Exception {
        return repository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
