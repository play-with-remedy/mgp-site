package com.pwr.mgp.entity;

import com.pwr.mgp.enums.TournamentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@ToString(exclude = {"games", "organization"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="tournaments", schema = "mgp")
public class Tournament {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "start_date", nullable = true) // updatable
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = true) // updatable
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = true, length = 25) // updatable
    private TournamentType type;

    @Column(name = "address", length = 100)
    private String address;

    @OneToMany(mappedBy = "tournament")
    @OrderBy("gameNumber ASC, tableNumber ASC")
    private List<Game> games;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
