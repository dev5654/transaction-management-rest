package uz.ml.delivering_rest.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.ml.delivering_rest.dto.product.ProductCreateDTO;
import uz.ml.delivering_rest.dto.product.ProductGetDTO;
import uz.ml.delivering_rest.entity.entity.Product;
import uz.ml.delivering_rest.mapper.mapper.ProductMapper;
import uz.ml.delivering_rest.repository.repository.ProductRepository;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.AbstractService;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductService extends AbstractService<ProductMapper, ProductRepository> {
    public ProductService(ProductMapper mapper, ProductRepository repository) {
        super(mapper, repository);
    }

    public ResponseEntity<Data<ProductGetDTO>> create(ProductCreateDTO createDTO) {
        Product product = mapper.fromCreateDTO(createDTO);
        Product save = repository.save(product);
        return new ResponseEntity<>(new Data<>(mapper.toGetDTO(save)), HttpStatus.OK);
    }
}
