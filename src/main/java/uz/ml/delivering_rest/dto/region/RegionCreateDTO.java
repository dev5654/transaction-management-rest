package uz.ml.delivering_rest.dto.region;

import lombok.*;
import uz.ml.delivering_rest.dto.BaseDTO;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegionCreateDTO implements BaseDTO {
    private String name;
    private String otherFields;
}
