package uz.ml.delivering_rest.mapper.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.ml.delivering_rest.dto.product.ProductCreateDTO;
import uz.ml.delivering_rest.dto.product.ProductGetDTO;
import uz.ml.delivering_rest.entity.entity.Product;
import uz.ml.delivering_rest.mapper.GenericMapper;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<ProductCreateDTO, ProductGetDTO, Product> {
}
