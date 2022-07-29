package uz.ml.delivering_rest.mapper.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.ml.delivering_rest.mapper.BaseMapper;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper {
}
