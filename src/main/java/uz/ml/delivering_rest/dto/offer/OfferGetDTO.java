package uz.ml.delivering_rest.dto.offer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.ml.delivering_rest.dto.GenericDTO;
import uz.ml.delivering_rest.entity.entity.Product;
import uz.ml.delivering_rest.entity.entity.Region;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OfferGetDTO extends GenericDTO {
    private String regionName;
    private String productName;
}
