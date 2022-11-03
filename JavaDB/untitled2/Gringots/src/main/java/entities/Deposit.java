package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Deposit {
    @Id
    @Column
    private long id;

    @Column(length = 20)
    private long group;

    @Column
    private LocalDate tartData;

    @Column
    private Double amount;

    @Column
    private Double interest;

    @Column
    private Double charge;

    @Column
    private LocalDate expiration_data;

    @Column
    private boolean is_expired;
}
