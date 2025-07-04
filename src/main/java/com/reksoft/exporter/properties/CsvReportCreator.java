package com.reksoft.exporter.properties;

import com.reksoft.exporter.service.CsvReportGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;

@Component
@RequiredArgsConstructor
public class CsvReportCreator {

    private final Clock clock;

    public ResponseEntity<Resource> buildCsvDownloadResponse(String reportPrefix,
                                                             CsvReportGenerator reportGenerator) throws IOException {
        String timestamp = LocalDateTime.now(clock)
                .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "%s_%s.csv".formatted(reportPrefix, timestamp);

        String path = System.getProperty("java.io.tmpdir") + File.separator + filename;
        File reportFile = reportGenerator.generate(path);

        FileSystemResource resource = new FileSystemResource(reportFile);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentLength(Files.size(reportFile.toPath()))
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);
    }
}
