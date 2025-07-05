package com.reksoft.exporter.mapper;

import com.reksoft.exporter.model.Team;
import com.reksoft.exporter.repository.dto.TeamViewDto;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {

    public Team map(TeamViewDto teamDto) {
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
