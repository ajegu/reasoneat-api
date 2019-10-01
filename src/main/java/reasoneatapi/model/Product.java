package reasoneatapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    private Collection<Month> months;

}
