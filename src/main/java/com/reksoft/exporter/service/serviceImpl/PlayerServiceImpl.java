package com.reksoft.exporter.service.serviceImpl;

import com.reksoft.exporter.mapper.PlayerMapper;
import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.repositoryImpl.PlayerApiRepository;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import com.reksoft.exporter.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerApiRepository playerApiRepository;
    private final PlayerMapper playerMapper;

    @Override
    public List<Player> getPlayers() {
        return playerApiRepository.getPlayers().stream()
                .map(playerMapper::toEntity)
                .toList();
    }
}
