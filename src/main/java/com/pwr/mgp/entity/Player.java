package com.pwr.mgp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pwr.mgp.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="players", schema = "mgp")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    private List<GamePlayer> participations;

    @OneToMany(mappedBy = "firstKilled")
    private List<Game> firstKills;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private Organization organization;
}
