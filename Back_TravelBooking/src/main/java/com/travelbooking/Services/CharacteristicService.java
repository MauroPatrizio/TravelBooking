package com.travelbooking.Services;

import com.travelbooking.DTO.CharacteristicDTO;
import com.travelbooking.Entities.Characteristic;
import com.travelbooking.Repositories.CharacteristicRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacteristicService extends BaseService<Characteristic, Long, CharacteristicRepository> {

    public CharacteristicService(CharacteristicRepository repository) {
        super(repository);
    }

    private CharacteristicDTO toDTO(Characteristic entity) {
        return new CharacteristicDTO(
                entity.getId(),
                entity.getKitchen(),
                entity.getBedrooms(),
                entity.getTv(),
                entity.getPets(),
                entity.getParking(),
                entity.getAir_conditioning(),
                entity.getWifi(),
                entity.getPool()
        );
    }

    private Characteristic toEntity(CharacteristicDTO dto) {
        Characteristic entity = new Characteristic();
        entity.setId(dto.getId());
        entity.setKitchen(dto.getKitchen());
        entity.setBedrooms(dto.getBedrooms());
        entity.setTv(dto.getTv());
        entity.setPets(dto.getPets());
        entity.setParking(dto.getParking());
        entity.setAir_conditioning(dto.getAir_conditioning());
        entity.setWifi(dto.getWifi());
        entity.setPool(dto.getPool());
        entity.setActive(true);
        return entity;
    }

    public CharacteristicDTO findDTOById(Long id) throws Exception {
        Optional<Characteristic> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new Exception("Characteristic not found with id: " + id);
        }
        return toDTO(entity.get());
    }

    public CharacteristicDTO createFromDTO(CharacteristicDTO dto) throws Exception {
        Characteristic entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public CharacteristicDTO updateFromDTO(CharacteristicDTO dto) throws Exception {
        if (!repository.existsById(dto.getId())) {
            throw new Exception("Characteristic not found with id: " + dto.getId());
        }
        Characteristic entity = toEntity(dto);
        return toDTO(repository.save(entity));
    }

    public List<CharacteristicDTO> findAllDTO() throws Exception {
        return repository.findByActiveTrue()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
