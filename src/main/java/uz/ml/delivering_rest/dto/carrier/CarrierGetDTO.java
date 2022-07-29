package uz.ml.delivering_rest.dto.carrier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import uz.ml.delivering_rest.dto.GenericDTO;
import uz.ml.delivering_rest.entity.entity.Region;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"region"})
public class CarrierGetDTO extends GenericDTO {
    private String name;

    @JsonProperty("region")
    private List<Region> regions;
}
