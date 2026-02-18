package com.pwr.mgp.entity;

import com.pwr.mgp.enums.OrganizationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="organizations", schema = "mgp")
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
