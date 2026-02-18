package com.pwr.mgp.dto;

import com.pwr.mgp.entity.Participation;
import com.pwr.mgp.entity.Player;
import com.pwr.mgp.entity.Team;
import com.pwr.mgp.entity.Tournament;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParticipationDto {
    private PlayerDto player;
}
