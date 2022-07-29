package uz.ml.delivering_rest.mapper.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.ml.delivering_rest.dto.offer.OfferCreateDTO;
import uz.ml.delivering_rest.dto.offer.OfferGetDTO;
import uz.ml.delivering_rest.entity.entity.Offer;
import uz.ml.delivering_rest.mapper.GenericMapper;

@Component
@Mapper(componentModel = "spring")
public interface OfferMapper extends GenericMapper<OfferCreateDTO, OfferGetDTO, Offer> {
}
