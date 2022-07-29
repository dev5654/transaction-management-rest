package uz.ml.delivering_rest.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.ml.delivering_rest.dto.offer.OfferCreateDTO;
import uz.ml.delivering_rest.entity.entity.Offer;
import uz.ml.delivering_rest.mapper.mapper.OfferMapper;
import uz.ml.delivering_rest.repository.repository.OfferRepository;
import uz.ml.delivering_rest.repository.repository.ProductRepository;
import uz.ml.delivering_rest.repository.repository.RegionRepository;
import uz.ml.delivering_rest.response.ApplicationError;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.AbstractService;

import javax.transaction.Transactional;

@Service
@Transactional
public class OfferService extends AbstractService<OfferMapper, OfferRepository> {
    public OfferService(OfferMapper mapper, OfferRepository repository, RegionRepository regionRepository, ProductRepository productRepository) {
        super(mapper, repository);
        this.regionRepository = regionRepository;
        this.productRepository = productRepository;
    }

    private final RegionRepository regionRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<Data<Long>> create(OfferCreateDTO createDTO) {
        if (!regionRepository.existsByName(createDTO.getRegionName())) {
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("Region not found").build()), HttpStatus.BAD_REQUEST);
        }
        if (!productRepository.existsById(createDTO.getProductId())) {
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("Product not found").build()), HttpStatus.BAD_REQUEST);
        }
        Offer offer = mapper.fromCreateDTO(createDTO);
        offer.setRegion(regionRepository.findByName(createDTO.getRegionName()));
        offer.setProduct(productRepository.getById(createDTO.getProductId()));
        return new ResponseEntity<>(new Data<>(repository.save(offer).getId()), HttpStatus.CREATED);
    }
}
