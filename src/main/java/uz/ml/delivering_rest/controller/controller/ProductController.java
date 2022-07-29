package uz.ml.delivering_rest.controller.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.ml.delivering_rest.controller.AbstractController;
import uz.ml.delivering_rest.dto.product.ProductCreateDTO;
import uz.ml.delivering_rest.dto.product.ProductGetDTO;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.service.ProductService;

@RestController
@RequestMapping(value = "/product/*")
public class ProductController extends AbstractController<ProductService> {
    public ProductController(ProductService service) {
        super(service);
    }

    @PostMapping("add")
    public ResponseEntity<Data<ProductGetDTO>> createProduct(@RequestBody ProductCreateDTO createDTO) {
        return service.create(createDTO);
    }
}
