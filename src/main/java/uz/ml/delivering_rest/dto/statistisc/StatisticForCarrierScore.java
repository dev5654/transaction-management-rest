package uz.ml.delivering_rest.dto.statistisc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ml.delivering_rest.dto.carrier.CarrierGetDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticForCarrierScore {
    private CarrierGetDTO carrier;
    private int sumScore;
}
