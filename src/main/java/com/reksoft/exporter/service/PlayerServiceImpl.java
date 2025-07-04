package com.reksoft.exporter.service;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.PlayerApiRepository;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerApiRepository playerApiRepository;

    @Override
    public List<Player> getPlayers() {
        List<PlayerViewDto> playerViewDtos = playerApiRepository.getPlayers();
        return playerViewDtos.stream().map(this::map).toList();
    }

    private String getFullNameWithNickName(String combinedName, String nickName) {
        if (combinedName == null || combinedName.isBlank()) {
            return nickName != null ? "\"" + nickName + "\"" : "";
        }

        String[] fullName = combinedName.trim().split(" ");
        String firstName = "";
        String nickNamePart = "";
        String lastName = "";
        if (fullName.length > 0) {
            firstName = fullName[0];
        }
        if (fullName.length > 1) {
            lastName = String.join(" ", Arrays.copyOfRange(fullName, 1, fullName.length));
        }
        if (nickName != null && !nickName.isBlank()) {
            nickNamePart = "\"" + nickName + "\"";
        }

        return String.format("%s %s %s", firstName, nickNamePart, lastName).trim();
    }

    private Player map(PlayerViewDto playerViewDto) {
        Player player = new Player();
        player.setId(playerViewDto.getId());
        player.setFullName(playerViewDto.getCombinedName());
        player.setNickname(playerViewDto.getNickName());
        player.setCountry(playerViewDto.getCountry());
        player.setTeamName(playerViewDto.getTeamName());
        player.setFullNameWithNickName(getFullNameWithNickName(player.getFullName(), player.getNickname()));
        return player;
    }
}
