package com.pwr.mgp.entity;

import com.pwr.mgp.enums.GameResult;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"tournament", "participants"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="games", schema = "mgp")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id")
    private Tournament  tournament;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GamePlayer> participants;

    @Column(name = "judge")
    private String judge;

    @Column(name = "table_number")
    private Integer tableNumber;
}
