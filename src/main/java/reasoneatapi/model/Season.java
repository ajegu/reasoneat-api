package reasoneatapi.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Season {

    @Id
    @GeneratedValue
    @Column(name = "season_id")
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "season")
    private List<Month> months;
}
