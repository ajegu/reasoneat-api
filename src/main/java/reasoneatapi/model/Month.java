package reasoneatapi.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Entity
public class Month {

    @Id
    @GeneratedValue
    @Column(name = "month_id")
    private UUID id;

    private int code;

    @ManyToMany(mappedBy = "months")
    private Collection<Product> products;

    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;
}
