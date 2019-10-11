package reasoneatapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_month",
            joinColumns = {@JoinColumn(name = "month_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Collection<Month> months;

}
