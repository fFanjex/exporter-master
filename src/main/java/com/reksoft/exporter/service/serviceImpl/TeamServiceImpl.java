package com.reksoft.exporter.service.serviceImpl;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import com.reksoft.exporter.repository.repositoryImpl.TeamApiRepository;
import com.reksoft.exporter.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamApiRepository teamApiRepository;

    @Override
    public List<Team> getAllTeams() {
        List<TeamViewDto> result = teamApiRepository.getTeams();
        return result.stream()
                .map(this::map)
                .toList();
    }

    private Team map(TeamViewDto teamDto) {
        Team team = new Team();
        team.setId(teamDto.getId());
        team.setName(teamDto.getName());
        String players = teamDto.getPlayers() == null ? "" : teamDto.getPlayers().stream()
                .map(p -> p.getCombinedName())
                .filter(name -> name != null && !name.isBlank())
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        team.setPlayers(players);
        return team;
    }
}
