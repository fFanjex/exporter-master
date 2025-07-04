package com.reksoft.exporter.controller;

import com.reksoft.exporter.properties.CsvReportCreator;
import com.reksoft.exporter.service.serviceImpl.CsvReportService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {

    private final CsvReportCreator csvReportCreator;
    private final CsvReportService csvReportService;

    @GetMapping
    public String getReportPage() {
        return "report";
    }

    @GetMapping("/player/download")
    public ResponseEntity<Resource> downloadPlayerReport() throws IOException {
        return csvReportCreator.buildCsvDownloadResponse("player_report", csvReportService::generatePlayerReport);
    }

    @GetMapping("/team/download")
    public ResponseEntity<Resource> downloadTeamReport() throws IOException {
        return csvReportCreator.buildCsvDownloadResponse("team_report", csvReportService::generateTeamReport);
    }
}
