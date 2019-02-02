package br.com.deordines.dataanalysis.monitor;

import br.com.deordines.dataanalysis.config.DirectoryPathConfig;
import br.com.deordines.dataanalysis.exception.FailMonitorException;
import br.com.deordines.dataanalysis.service.FileDataService;
import br.com.deordines.dataanalysis.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class Monitor {

    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

    private final FileService fileService;
    private final FileDataService fileDataService;

    public Monitor(FileService fileService, FileDataService fileDataService) {
        this.fileService = fileService;
        this.fileDataService = fileDataService;
    }

    @Scheduled(fixedRateString = "${monitor.fixed-rate}")
    public void monitor() {
        try {
            logger.info("Monitoring folder.");
            Path folderPath = fileService.getPath(DirectoryPathConfig.getIN());
            List<Path> folderContent = fileService.walk(folderPath);
            folderContent.stream()
                    .filter(fileService::isFile)
                    .findAny()
                    .ifPresent(fileDataService::process);
        } catch (Exception e) {
            logger.error("Error to monitoring folder");
            throw new FailMonitorException();
        }
    }
}