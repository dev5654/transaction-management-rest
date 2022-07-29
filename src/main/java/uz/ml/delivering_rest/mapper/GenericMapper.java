package uz.ml.delivering_rest.mapper;

import uz.ml.delivering_rest.dto.BaseDTO;
import uz.ml.delivering_rest.dto.GenericDTO;
import uz.ml.delivering_rest.entity.Auditable;

import java.util.List;

public interface GenericMapper<CD extends BaseDTO, GD extends GenericDTO, E extends Auditable> extends BaseMapper {
    E fromCreateDTO(CD dto);

    GD toGetDTO(E entity);

    List<GD> toGetDTO(List<E> entities);
}
