package uz.ml.delivering_rest.entity.entity;

import lombok.*;
import uz.ml.delivering_rest.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Carrier extends Auditable {

    private String name;

    @ManyToMany(targetEntity = Region.class, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Region> regions = new ArrayList<>();
}
