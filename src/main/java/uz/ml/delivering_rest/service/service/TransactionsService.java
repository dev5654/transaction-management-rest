package uz.ml.delivering_rest.service.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.ml.delivering_rest.dto.transactions.TransactionsCreateDTO;
import uz.ml.delivering_rest.entity.entity.Carrier;
import uz.ml.delivering_rest.entity.entity.Offer;
import uz.ml.delivering_rest.entity.entity.Request;
import uz.ml.delivering_rest.entity.entity.Transactions;
import uz.ml.delivering_rest.mapper.mapper.TransactionsMapper;
import uz.ml.delivering_rest.repository.repository.CarrierRepository;
import uz.ml.delivering_rest.repository.repository.OfferRepository;
import uz.ml.delivering_rest.repository.repository.RequestRepository;
import uz.ml.delivering_rest.repository.repository.TransactionsRepository;
import uz.ml.delivering_rest.response.ApplicationError;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.AbstractService;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class TransactionsService extends AbstractService<TransactionsMapper, TransactionsRepository> {

    private final CarrierRepository carrierRepository;
    private final RequestRepository requestRepository;
    private final OfferRepository offerRepository;

    public TransactionsService(TransactionsMapper mapper, TransactionsRepository repository, CarrierRepository carrierRepository, RequestRepository requestRepository, OfferRepository offerRepository) {
        super(mapper, repository);
        this.carrierRepository = carrierRepository;
        this.requestRepository = requestRepository;
        this.offerRepository = offerRepository;
    }

    public ResponseEntity<Data<Long>> create(TransactionsCreateDTO createDTO) {
        Optional<Carrier> optionalCarrier = carrierRepository.findByName(createDTO.getCarrierName());
        Optional<Request> optionalRequest = requestRepository.findById(createDTO.getRequestId());
        Optional<Offer> optionalOffer = offerRepository.findById(createDTO.getOfferId());
        if (optionalOffer.isEmpty() || optionalCarrier.isEmpty() || optionalRequest.isEmpty())
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("carrier or request or offer not found").build()), HttpStatus.BAD_REQUEST);
        if (repository.existsByOfferAndRequest(optionalOffer.get(), optionalRequest.get()))
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("this transaction already exist").build()), HttpStatus.BAD_REQUEST);
        if (!Objects.equals(optionalRequest.get().getProduct().getId(), optionalOffer.get().getProduct().getId()))
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("request and offer do not belong to the same product ID").build()), HttpStatus.BAD_REQUEST);
        if (optionalCarrier.get().getRegions().stream().noneMatch(o -> Objects.equals(o.getId(), optionalRequest.get().getRegion().getId())))
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("Carrier must serve both delivery and pickup locations").build()), HttpStatus.BAD_REQUEST);
        Transactions transactions = mapper.fromCreateDTO(createDTO);
        transactions.setCarrier(optionalCarrier.get());
        transactions.setOffer(optionalOffer.get());
        transactions.setRequest(optionalRequest.get());
        transactions.getRequest().getRegion().setTransactionCount(transactions.getRequest().getRegion().getTransactionCount() + 1);
        transactions.getOffer().getProduct().setTransactionCount(transactions.getOffer().getProduct().getTransactionCount() + 1);
        return new ResponseEntity<>(new Data<>(repository.save(transactions).getId()), HttpStatus.OK);
    }

    public ResponseEntity<Data<Boolean>> evaluateTransaction(Long transactionId, int score) {
        Optional<Transactions> optional = repository.findById(transactionId);
        if (optional.isEmpty())
            return new ResponseEntity<>(new Data<>(ApplicationError.builder().message("transaction not found").build()), HttpStatus.BAD_REQUEST);
        if (score < 1 || score > 10)
            return new ResponseEntity<>(new Data<>(false), HttpStatus.BAD_REQUEST);
        Transactions transactions = optional.get();
        transactions.setScore(score);
        repository.save(transactions);
        return new ResponseEntity<>(new Data<>(true), HttpStatus.OK);
    }
}
