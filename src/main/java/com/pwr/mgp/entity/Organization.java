package com.pwr.mgp.entity;

import com.pwr.mgp.enums.OrganizationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="organizations", schema = "mgp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "foundation_date")
    private LocalDate foundationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false, length = 25)
    private OrganizationType type;

    @OneToOne
    @JoinColumn(name="director_id")
    private Player director;

    @OneToMany(mappedBy = "organization")
    private List<Tournament> tournaments;

    @OneToMany(mappedBy = "organization")
    private List<Player> players;
}
