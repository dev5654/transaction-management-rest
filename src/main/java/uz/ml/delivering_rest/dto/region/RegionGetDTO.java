package uz.ml.delivering_rest.dto.region;

import lombok.*;
import uz.ml.delivering_rest.dto.GenericDTO;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegionGetDTO extends GenericDTO {
    private String name;
    private String otherFields;
}
