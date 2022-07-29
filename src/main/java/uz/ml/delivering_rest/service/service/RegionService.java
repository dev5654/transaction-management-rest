package uz.ml.delivering_rest.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.ml.delivering_rest.dto.region.RegionCreateDTO;
import uz.ml.delivering_rest.dto.region.RegionGetDTO;
import uz.ml.delivering_rest.response.ApplicationError;
import uz.ml.delivering_rest.entity.entity.Region;
import uz.ml.delivering_rest.mapper.mapper.RegionMapper;
import uz.ml.delivering_rest.repository.repository.RegionRepository;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.AbstractService;

import javax.persistence.OrderBy;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class RegionService extends AbstractService<RegionMapper, RegionRepository> {
    public RegionService(RegionMapper mapper, RegionRepository repository) {
        super(mapper, repository);
    }

    public ResponseEntity<Data<RegionGetDTO>> create(RegionCreateDTO createDTO) {
        Region region = mapper.fromCreateDTO(createDTO);
        if (repository.existsByName(region.getName()))
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("region already exist").build()), HttpStatus.BAD_REQUEST);
        Region save = repository.save(region);
        return new ResponseEntity<>(new Data<>(mapper.toGetDTO(save)), HttpStatus.CREATED);
    }

    public ResponseEntity<Data<?>> getAll() {
        List<Region> all = repository.findAll();
        List<Region> regions = all.stream().sorted(Comparator.comparing(Region::getName)).toList();
        return new ResponseEntity<>(new Data<>(mapper.toGetDTO(regions)), HttpStatus.OK);
    }
}
