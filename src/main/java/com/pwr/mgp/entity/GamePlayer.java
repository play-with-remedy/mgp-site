package com.pwr.mgp.entity;

import com.pwr.mgp.enums.GameRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
