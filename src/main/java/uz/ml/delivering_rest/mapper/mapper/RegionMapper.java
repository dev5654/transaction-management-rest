package uz.ml.delivering_rest.mapper.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.ml.delivering_rest.dto.region.RegionCreateDTO;
import uz.ml.delivering_rest.dto.region.RegionGetDTO;
import uz.ml.delivering_rest.entity.entity.Region;
import uz.ml.delivering_rest.mapper.GenericMapper;

@Component
@Mapper(componentModel = "spring")
public interface RegionMapper extends GenericMapper<RegionCreateDTO, RegionGetDTO, Region> {
}
