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
public class Town extends BaseEntity{


    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Country country;

}
