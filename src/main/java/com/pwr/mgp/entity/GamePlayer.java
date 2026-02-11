package com.pwr.mgp.entity;

import com.pwr.mgp.enums.GameRole;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"game", "player"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(
        name = "players_games",
        schema = "mgp",
        uniqueConstraints = @UniqueConstraint(name = "uk_game_player", columnNames = {"game_id", "player_id"})
)
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private GameRole role;

    @Column(name = "place", nullable = false)
    private Integer place;

    @Column(name = "game_point")
    private Double gamePoint;

    @Column(name = "additional_point")
    private Double additionalPoint;

    @Column(name = "yellow_card")
    private Double yellowCard;

    @Column(name = "red_card")
    private Double redCard;

    @Column(name = "gray_card")
    private Double grayCard;

    @Column(name = "violet_card")
    private Double violetCard;

    @Column(name = "lateness")
    private Double lateness;
}
