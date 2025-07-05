package com.reksoft.exporter.service.serviceImpl;

import com.reksoft.exporter.mapper.TeamMapper;
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
    private final TeamMapper teamMapper;

    @Override
    public List<Team> getAllTeams() {
        List<TeamViewDto> result = teamApiRepository.getTeams();
        return result.stream()
                .map(teamMapper::map)
                .toList();
    }

}
