package uz.ml.delivering_rest.dto.statistisc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.ml.delivering_rest.dto.region.RegionGetDTO;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatisticForRegion {
    private int transactionNumber;
    List<RegionGetDTO> regions;
}
