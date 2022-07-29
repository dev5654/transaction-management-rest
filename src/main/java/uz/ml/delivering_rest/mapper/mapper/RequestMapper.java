package uz.ml.delivering_rest.mapper.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.ml.delivering_rest.dto.request.RequestCreateDTO;
import uz.ml.delivering_rest.dto.request.RequestGetDTO;
import uz.ml.delivering_rest.entity.entity.Request;
import uz.ml.delivering_rest.mapper.GenericMapper;

@Component
@Mapper(componentModel = "spring")
public interface RequestMapper extends GenericMapper<RequestCreateDTO, RequestGetDTO, Request> {
}
