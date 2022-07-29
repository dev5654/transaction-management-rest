package uz.ml.delivering_rest.entity.entity;

import lombok.*;
import uz.ml.delivering_rest.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Offer extends Auditable {
    @ManyToOne
    private Region region;

    @ManyToOne
    private Product product;
}
