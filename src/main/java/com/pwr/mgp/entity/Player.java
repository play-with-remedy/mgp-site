package com.pwr.mgp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pwr.mgp.enums.BDayVisibility;
import com.pwr.mgp.enums.UserRole;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="players", schema = "mgp")
public class Player {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 25, nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 25, nullable = false)
    private String lastName;

    @Column(name = "nickname", length = 25, nullable = false, unique = true)
    private String nickname;

    @Column(name = "quotation", length = 50)
    private String quotation;

    @Column(name = "start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "birthdate_visibility", nullable = true) // исправить потом
    private BDayVisibility birthdateVisibility;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    private List<GamePlayer> participations;

    @OneToMany(mappedBy = "firstKilled")
    private List<Game> firstKills;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Participation> teamParticipations = new HashSet<>();
}
