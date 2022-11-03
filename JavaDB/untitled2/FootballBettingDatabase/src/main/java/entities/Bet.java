package entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table
public class Bet extends BaseEntity implements Serializable {

    @Column
    private BigDecimal betmoney;

    @Column
    private Data timeOfBet;

    @Column
    @ManyToOne
    private User user;
}
