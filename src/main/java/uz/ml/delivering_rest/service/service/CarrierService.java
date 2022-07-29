package uz.ml.delivering_rest.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.ml.delivering_rest.dto.carrier.CarrierCreateDTO;
import uz.ml.delivering_rest.dto.carrier.CarrierGetDTO;
import uz.ml.delivering_rest.dto.region.RegionGetDTO;
import uz.ml.delivering_rest.entity.entity.Carrier;
import uz.ml.delivering_rest.entity.entity.Region;
import uz.ml.delivering_rest.mapper.mapper.CarrierMapper;
import uz.ml.delivering_rest.mapper.mapper.RegionMapper;
import uz.ml.delivering_rest.repository.repository.CarrierRepository;
import uz.ml.delivering_rest.repository.repository.RegionRepository;
import uz.ml.delivering_rest.response.ApplicationError;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.AbstractService;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class CarrierService extends AbstractService<CarrierMapper, CarrierRepository> {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    public CarrierService(CarrierMapper mapper, CarrierRepository repository, RegionRepository regionRepository, RegionMapper regionMapper) {
        super(mapper, repository);
        this.regionRepository = regionRepository;
        this.regionMapper = regionMapper;
    }

    public ResponseEntity<Data<CarrierGetDTO>> create(CarrierCreateDTO createDTO) {
        Carrier carrier = mapper.fromCreateDTO(createDTO);
        for (String name : createDTO.getRegionsName()) {
            if (regionRepository.existsByName(name)) {
                carrier.getRegions().add(regionRepository.findByName(name));
            }
        }
        return new ResponseEntity<>(new Data<>(mapper.toGetDTO(repository.save(carrier))), HttpStatus.CREATED);
    }

    public ResponseEntity<Data<List<CarrierGetDTO>>> getCarriersForRegion(String regionName) {
        Region region = regionRepository.findByName(regionName);
        if (Objects.nonNull(region)) {
            List<Carrier> carriers = region.getCarriers().stream().sorted(Comparator.comparing(Carrier::getName)).toList();;
            return new ResponseEntity<>(new Data<>(mapper.toGetDTO(carriers)), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("Region not found").build()), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Data<List<RegionGetDTO>>> getAll(Long carrierId) {
        Optional<Carrier> optional = repository.findById(carrierId);
        if (optional.isEmpty())
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("carrier not found").build()), HttpStatus.BAD_REQUEST);
        List<Region> regions = optional.get().getRegions().stream().sorted(Comparator.comparing(Region::getName)).toList();
        return new ResponseEntity<>(new Data<>(regionMapper.toGetDTO(regions)), HttpStatus.CREATED);
    }

}
