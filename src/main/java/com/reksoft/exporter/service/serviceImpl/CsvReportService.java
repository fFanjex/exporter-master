package com.reksoft.exporter.service.serviceImpl;

import com.opencsv.CSVWriter;
import com.reksoft.exporter.model.Player;
import com.reksoft.exporter.model.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvReportService {
    private final PlayerServiceImpl playerService;
    private final TeamServiceImpl teamService;

    public File generatePlayerReport(String path) throws IOException {
        List<Player> players = playerService.getPlayers();
        File file = new File(path);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = {"ID", "Combined Name", "Nickname", "Country", "Team Name", "Full Name"};
            writer.writeNext(header);

            for (Player player : players) {
                String[] row = {
                        String.valueOf(player.getId()),
                        player.getFullName(),
                        player.getNickname(),
                        player.getCountry() != null ? String.valueOf(player.getCountry()) : "",
                        player.getTeamName(),
                        player.getFullNameWithNickName()
                };
                writer.writeNext(row);
            }
        }
        return file;
    }

    public File generateTeamReport(String path) throws IOException {
        List<Team> teams = teamService.getAllTeams();
        File file = new File(path);
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            String[] header = {"ID", "TeamName", "Players"};
            writer.writeNext(header);

            for (Team team : teams) {
                String[] row = {
                        String.valueOf(team.getId()),
                        team.getName(),
                        String.valueOf(team.getPlayers())
                };
                writer.writeNext(row);
            }
        }
        return file;
    }
}
