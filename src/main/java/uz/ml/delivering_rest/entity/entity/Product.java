package uz.ml.delivering_rest.entity.entity;

import lombok.*;
import uz.ml.delivering_rest.entity.Auditable;

import javax.persistence.Entity;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product extends Auditable {
    private String name;
    private String description;
    private int price;
    private Integer transactionCount = 0;
}
