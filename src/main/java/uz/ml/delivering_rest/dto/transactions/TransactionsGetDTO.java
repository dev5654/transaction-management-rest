package uz.ml.delivering_rest.dto.transactions;

import lombok.*;
import uz.ml.delivering_rest.dto.GenericDTO;
import uz.ml.delivering_rest.entity.entity.Carrier;
import uz.ml.delivering_rest.entity.entity.Offer;
import uz.ml.delivering_rest.entity.entity.Request;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionsGetDTO extends GenericDTO {
    private Carrier carrier;
    private Request request;
    private Offer offer;
}
