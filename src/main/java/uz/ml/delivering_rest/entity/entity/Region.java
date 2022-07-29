package uz.ml.delivering_rest.entity.entity;

import lombok.*;
import uz.ml.delivering_rest.entity.Auditable;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Region extends Auditable {
    @Column(unique = true)
    private String name;
    private String otherFields;
    private Integer transactionCount = 0;
    @ManyToMany(mappedBy = "regions", fetch = FetchType.EAGER)
    private List<Carrier> carriers;
}
