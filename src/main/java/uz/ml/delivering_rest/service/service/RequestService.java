package uz.ml.delivering_rest.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.ml.delivering_rest.dto.request.RequestCreateDTO;
import uz.ml.delivering_rest.entity.entity.Product;
import uz.ml.delivering_rest.entity.entity.Request;
import uz.ml.delivering_rest.mapper.mapper.RequestMapper;
import uz.ml.delivering_rest.repository.repository.ProductRepository;
import uz.ml.delivering_rest.repository.repository.RegionRepository;
import uz.ml.delivering_rest.repository.repository.RequestRepository;
import uz.ml.delivering_rest.response.ApplicationError;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.AbstractService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RequestService extends AbstractService<RequestMapper, RequestRepository> {

    private final RegionRepository regionRepository;
    private final ProductRepository productRepository;

    public RequestService(RequestMapper mapper, RequestRepository repository, RegionRepository regionRepository, ProductRepository productRepository) {
        super(mapper, repository);
        this.regionRepository = regionRepository;
        this.productRepository = productRepository;
    }

    public ResponseEntity<Data<Long>> addRequest(RequestCreateDTO createDTO) {
        Optional<Product> optionalProduct = productRepository.findById(createDTO.getProductId());
        if (!regionRepository.existsByName(createDTO.getRegionName()))
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("region not found").build()), HttpStatus.BAD_REQUEST);
        if (optionalProduct.isEmpty())
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("product not found").build()), HttpStatus.BAD_REQUEST);
        Request request = new Request();
        request.setProduct(optionalProduct.get());
        request.setRegion(regionRepository.findByName(createDTO.getRegionName()));
        return new ResponseEntity<>(new Data<>(repository.save(request).getId()), HttpStatus.OK);
    }
}
