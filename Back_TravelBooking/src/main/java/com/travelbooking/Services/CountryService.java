package com.travelbooking.Services;

import com.travelbooking.DTO.CountryDTO;
import com.travelbooking.Entities.Country;
import com.travelbooking.Repositories.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CountryService extends BaseService<Country, Long, CountryRepository> {

    public CountryService(CountryRepository repository) {
        super(repository);
    }

    private CountryDTO toDTO(Country entity) {
        return new CountryDTO(
                entity.getId(),
                entity.getName()
        );
    }

    private Country toEntity(CountryDTO dto) {
        Country entity = new Country();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setActive(true);
        return entity;
    }

    public CountryDTO findDTOById(Long id) throws Exception {
        Optional<Country> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("Country not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public CountryDTO createFromDTO(CountryDTO dto) throws Exception {
        Country entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public CountryDTO updateFromDTO(CountryDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("Country not found with id: " + dto.getId());
        }
        Country entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public List<CountryDTO> findAllDTO() throws Exception {
        return repository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
