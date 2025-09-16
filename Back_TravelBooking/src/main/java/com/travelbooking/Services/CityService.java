package com.travelbooking.Services;

import com.travelbooking.DTO.CityDTO;
import com.travelbooking.DTO.CountryDTO;
import com.travelbooking.Entities.City;
import com.travelbooking.Entities.Country;
import com.travelbooking.Repositories.CityRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CityService extends BaseService<City, Long, CityRepository> {

    public CityService(CityRepository repository) {
        super(repository);
    }

    private CityDTO toDTO(City entity) {
        Country country = entity.getCountry();
        CountryDTO countryDTO = new CountryDTO(
                country.getId(),
                country.getName()
        );

        return new CityDTO(
                entity.getId(),
                entity.getName(),
                countryDTO
        );
    }

    private City toEntity(CityDTO dto) {
        Country country = new Country();
        if (dto.getCountry() != null) {
            country.setId(dto.getCountry().getId());
            country.setName(dto.getCountry().getName());
        }

        City entity = new City();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCountry(country);
        entity.setActive(true);
        return entity;
    }

    public CityDTO findDTOById(Long id) throws Exception {
        Optional<City> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("City not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public CityDTO createFromDTO(CityDTO dto) throws Exception {
        City entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public CityDTO updateFromDTO(CityDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("City not found with id: " + dto.getId());
        }
        City entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public Set<CityDTO> findAllDTO() throws Exception {
        return repository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toSet());
    }
}
