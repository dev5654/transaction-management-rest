package uz.ml.delivering_rest.dto.request;

import lombok.*;
import uz.ml.delivering_rest.dto.GenericDTO;
import uz.ml.delivering_rest.entity.entity.Product;
import uz.ml.delivering_rest.entity.entity.Region;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestGetDTO extends GenericDTO {
    private Region region;
    private Product product;
}
