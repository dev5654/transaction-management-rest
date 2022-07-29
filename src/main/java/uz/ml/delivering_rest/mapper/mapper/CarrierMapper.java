package uz.ml.delivering_rest.mapper.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.ml.delivering_rest.dto.carrier.CarrierCreateDTO;
import uz.ml.delivering_rest.dto.carrier.CarrierGetDTO;
import uz.ml.delivering_rest.entity.entity.Carrier;
import uz.ml.delivering_rest.mapper.GenericMapper;

@Component
@Mapper(componentModel = "spring")
public interface CarrierMapper extends GenericMapper<CarrierCreateDTO, CarrierGetDTO, Carrier> {

}
