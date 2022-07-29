package uz.ml.delivering_rest.controller.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ml.delivering_rest.controller.AbstractController;
import uz.ml.delivering_rest.dto.offer.OfferCreateDTO;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.service.OfferService;

@RestController
@RequestMapping(value = "/offer/*")
public class OfferController extends AbstractController<OfferService> {
    public OfferController(OfferService service) {
        super(service);
    }

    @PostMapping("add")
    public ResponseEntity<Data<Long>> addOffer(@RequestBody OfferCreateDTO createDTO) {
        return service.create(createDTO);
    }
}
