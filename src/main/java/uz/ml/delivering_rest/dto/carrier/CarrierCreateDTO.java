package uz.ml.delivering_rest.dto.carrier;

import lombok.*;
import uz.ml.delivering_rest.dto.BaseDTO;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarrierCreateDTO implements BaseDTO {

    private String name;

    private List<String> regionsName;
}
