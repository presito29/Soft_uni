package entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table
public class Competition extends BaseEntity{

    @Column
    private String name;

    @ManyToOne
    private CompetitionType type;
}
