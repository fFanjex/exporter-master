package com.reksoft.exporter.mapper;

import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.repository.dto.PlayerViewDto;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class PlayerMapper {

    public Player toEntity(PlayerViewDto dto) {
        if (dto == null) return null;
        Player player = new Player();
        player.setId(dto.getId());
        player.setFullName(dto.getCombinedName());
        player.setNickname(dto.getNickName());
        player.setCountry(dto.getCountry());
        player.setTeamName(dto.getTeamName());
        player.setFullNameWithNickName(getFullNameWithNickName(dto.getCombinedName(), dto.getNickName()));
        return player;
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
}