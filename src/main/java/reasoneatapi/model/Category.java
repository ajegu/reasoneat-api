package reasoneatapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "category")
    private Collection<Product> products;
}
