package com.pwr.mgp.service.impl;

import com.pwr.mgp.dto.PlayerDto;
import com.pwr.mgp.entity.Player;
import com.pwr.mgp.enums.UserRole;
import com.pwr.mgp.mapper.PlayerMapper;
import com.pwr.mgp.record.PlayerFilter;
import com.pwr.mgp.repository.PlayerRepository;
import com.pwr.mgp.service.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final PasswordEncoder passwordEncoder;

    public PlayerServiceImpl(PlayerRepository playerRepository, PlayerMapper playerMapper, PasswordEncoder passwordEncoder) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<PlayerDto> getPlayers(PlayerFilter filter) {

        Sort sort = Sort.by(Sort.Direction.ASC, "nickname");
        Specification<Player> spec = (root, query, cb) -> cb.conjunction();

        Long orgId = filter != null ? filter.organizationId() : null;
        if (orgId != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("organization").get("id"), orgId));
        }

        Long tournamentId = filter != null ? filter.tournamentId() : null;
        if (tournamentId != null) {
            spec = spec.and((root, query, cb) -> {
                // чтобы не получить дубли игрока из-за join'а
                query.distinct(true);

                var reg = root.join("tournamentRegistrations");          // List<TournamentPlayer>
                var tournament = reg.get("tournament");                  // Tournament
                return cb.equal(tournament.get("id"), tournamentId);
            });
        }

        return playerMapper.toDto(playerRepository.findAll(spec, sort));
    }

    @Override
    public PlayerDto getPlayerById(Long id) {
        return playerMapper.toDto(playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found: " + id)));
    }

    @Override
    public PlayerDto addPlayer(@NotNull Player player) {
        player.setRole(UserRole.USER);

        String userPassword = player.getPassword();
        if (StringUtils.isNotBlank(userPassword)) {
            player.setPassword(passwordEncoder.encode(userPassword));
        }

        return playerMapper.toDto(playerRepository.save(player));
    }

    @Override
    public PlayerDto updatePlayerById(Long id, @NotNull Player player) {
        Player p = playerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Player not found: " + id));

        if (StringUtils.isNotBlank(player.getPassword())) p.setPassword(passwordEncoder.encode(player.getPassword()));
        if (StringUtils.isNotBlank(player.getNickname())) p.setNickname(player.getNickname());
        if (StringUtils.isNotBlank(player.getLastName())) p.setLastName(player.getLastName());
        if (StringUtils.isNotBlank(player.getFirstName())) p.setFirstName(player.getFirstName());
        if (StringUtils.isNotBlank(player.getQuotation())) p.setQuotation(player.getQuotation());
        if (player.getDateOfBirth() != null) p.setDateOfBirth(player.getDateOfBirth());
        if (player.getStartDate() != null) p.setStartDate(player.getStartDate());
        if (player.getBirthdateVisibility() != null) p.setBirthdateVisibility(player.getBirthdateVisibility());
        if (player.getOrganization() != null) {
            if (player.getOrganization().getId() != null) {
                p.setOrganization(player.getOrganization());
            } else {
                p.setOrganization(null);
            }
        }

        return playerMapper.toDto(playerRepository.save(p));
    }

    @Override
    public void deletePlayerById(Long id) {
        if (!playerRepository.existsById(id)) throw new EntityNotFoundException("Player not found: " + id);
        playerRepository.deleteById(id);
    }

    @Override
    public PlayerDto loginPlayer(@NotNull Player player) {
        String rawPassword = player.getPassword();

        Player dbPlayer = playerRepository.findByNicknameIgnoreCase(player.getNickname())
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with nickname=" + player.getNickname() + " not found"
                ));

        if (StringUtils.isBlank(rawPassword) || !passwordEncoder.matches(rawPassword, dbPlayer.getPassword())) {
            throw new IllegalArgumentException("Bad credentials");
        }

        return playerMapper.toDto(dbPlayer);
    }
}
