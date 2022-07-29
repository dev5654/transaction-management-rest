package uz.ml.delivering_rest.dto.statistisc;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticForProduct {
    private Long productId;
    private int transactionCount;
}
