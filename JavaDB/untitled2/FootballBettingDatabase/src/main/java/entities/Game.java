package entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table
public class Game extends BaseEntity implements Serializable {


    @OneToOne
    @JoinColumn
    private Team homeTeam;

    @OneToOne
    @JoinColumn
    private Team awayTeam;

    @Column
    private Short homeGoals;

    @Column
    private Short awayGoals;

    @Column
    private Data dataAndTime;

    @Column
    private double homeTeamWinBetRate;

    @Column
    private double awayTeamWinBetRate;

    @Column
    private double drawGameBetRate;

    @ManyToOne
    @JoinColumn
    private Round round;

    @ManyToOne
    @JoinColumn
    private Competition competition ;
}
