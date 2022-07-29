package uz.ml.delivering_rest.controller.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ml.delivering_rest.controller.AbstractController;
import uz.ml.delivering_rest.dto.transactions.TransactionsCreateDTO;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.service.TransactionsService;

@RestController
@RequestMapping(value = "/transactions/*")
public class TransactionsController extends AbstractController<TransactionsService> {

    public TransactionsController(TransactionsService service) {
        super(service);
    }

    @PostMapping("add")
    public ResponseEntity<Data<Long>> createTransaction(@RequestBody TransactionsCreateDTO createDTO) {
        return service.create(createDTO);
    }
    @PatchMapping("evaluateTransaction")
    public ResponseEntity<Data<Boolean>> evaluateTransaction(Long transactionId,int score) {
        return service.evaluateTransaction(transactionId,score);
    }

}
