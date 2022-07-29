package uz.ml.delivering_rest.controller.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ml.delivering_rest.controller.AbstractController;
import uz.ml.delivering_rest.service.service.StatisticService;

@RestController
@RequestMapping(value = "/statistics/*")
public class StatisticController extends AbstractController<StatisticService> {

    public StatisticController(StatisticService service) {
        super(service);
    }

    @GetMapping("deliveryRegionsPerNumberOfTran")
    public ResponseEntity<?> deliveryRegionsPerNT() {
        return service.deliveryRegionsPerNT();
    }

    @GetMapping("numberOfTranPerProduct")
    public ResponseEntity<?> numberOfTranPerProduct() {
        return service.numberOfTranPerProduct();
    }

    @GetMapping("scorePerCarrier")
    public ResponseEntity<?> scorePerCarrier(Integer minimumScore) {
        return service.scorePerCarrier(minimumScore);
    }

}
