package entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Wizard {

    @Id
    @Column
    private long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 60)
    @NotNull
    private String lastName;

    @Column(length = 1000)
    private String note;

    @Column
    @NotNull
    private long age;

    @OneToOne
    @JoinColumn
    private MagicWand magicWand;

    @OneToMany
    @JoinTable(
            name = "wizard_deposit",
            joinColumns = @JoinColumn (name = "id"),
           inverseJoinColumns = @JoinColumn (name = "deposit_id")
    )
    private List<Deposit> deposits;
}

