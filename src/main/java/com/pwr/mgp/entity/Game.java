package com.pwr.mgp.entity;

import com.pwr.mgp.enums.GameResult;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="games", schema = "mgp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "result", nullable = false)
    private GameResult result;

    @Column(name = "game_number")
    private Integer gameNumber;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament  tournament;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GamePlayer> participants;

    @Column(name = "judge")
    private String judge;

    @Column(name = "table_number")
    private Integer tableNumber;
}
