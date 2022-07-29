package uz.ml.delivering_rest.controller.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ml.delivering_rest.controller.AbstractController;
import uz.ml.delivering_rest.dto.region.RegionCreateDTO;
import uz.ml.delivering_rest.dto.region.RegionGetDTO;
import uz.ml.delivering_rest.response.Data;
import uz.ml.delivering_rest.service.service.RegionService;

@RestController
@RequestMapping(value = "/region/*")
public class RegionController extends AbstractController<RegionService> {
    public RegionController(RegionService service) {
        super(service);
    }

    @PostMapping("add")
    public ResponseEntity<Data<RegionGetDTO>> addRegion(@RequestBody RegionCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @GetMapping("getAll")
    public ResponseEntity<Data<?>> getAllSortedRegions() {
        return service.getAll();
    }
}
