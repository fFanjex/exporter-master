package com.reksoft.exporter.service;

import java.io.File;
import java.io.IOException;

@FunctionalInterface
public interface CsvReportGenerator {
    File generate(String path) throws IOException;
}
