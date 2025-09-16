package com.travelbooking.Services;

import com.travelbooking.DTO.FavoriteDTO;
import com.travelbooking.Entities.Favorite;
import com.travelbooking.Entities.Product;
import com.travelbooking.Entities.User;
import com.travelbooking.Repositories.FavoriteRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteService extends BaseService<Favorite, Long, FavoriteRepository> {

    public FavoriteService(FavoriteRepository repository) {
        super(repository);
    }

    private FavoriteDTO toDTO(Favorite entity) {
        return new FavoriteDTO(
                entity.getId(),
                entity.getProduct(),
                entity.getUser()
        );
    }

    private Favorite toEntity(FavoriteDTO dto) {
        Favorite entity = new Favorite();
        entity.setId(dto.getId());

        Product product = dto.getProduct();
        if (product != null) {
            entity.setProduct(product);
        }

        User user = dto.getUser();
        if (user != null) {
            entity.setUser(user);
        }

        entity.setActive(true);
        return entity;
    }

    public FavoriteDTO findDTOById(Long id) throws Exception {
        Optional<Favorite> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("Favorite not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public FavoriteDTO createFromDTO(FavoriteDTO dto) throws Exception {
        Favorite entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public FavoriteDTO updateFromDTO(FavoriteDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("Favorite not found with id: " + dto.getId());
        }
        Favorite entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public Set<FavoriteDTO> findAllDTO() throws Exception {
        return repository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public Set<FavoriteDTO> findByUserId(Long userId) {
        return repository.findByActiveTrue()
                .stream()
                .filter(fav -> fav.getUser() != null && fav.getUser().getId().equals(userId))
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }

    public void deleteByProductId(Long productId, Long userId) {
        Set<Favorite> favorites = repository.findByActiveTrue();
        favorites.stream()
                .filter(fav -> fav.getProduct() != null && fav.getProduct().getId().equals(productId))
                .filter(fav -> fav.getUser() != null && fav.getUser().getId().equals(userId))
                .forEach(fav -> {
                    fav.setActive(false);
                    repository.save(fav);
                });
    }
}
