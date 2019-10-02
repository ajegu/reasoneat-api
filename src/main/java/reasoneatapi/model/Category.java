package reasoneatapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@Data
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Collection<Product> products;
}
