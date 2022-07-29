package uz.ml.delivering_rest.dto.transactions;

import lombok.*;
import uz.ml.delivering_rest.dto.BaseDTO;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsCreateDTO implements BaseDTO {
    private String carrierName;

    private Long requestId;

    private Long offerId;
}
