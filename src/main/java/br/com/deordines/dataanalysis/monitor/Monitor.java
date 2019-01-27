package br.com.deordines.dataanalysis.monitor;

import br.com.deordines.dataanalysis.exception.CustomException;
import br.com.deordines.dataanalysis.exception.FailMonitorException;
import br.com.deordines.dataanalysis.helper.FileHelper;
import br.com.deordines.dataanalysis.service.FileDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class Monitor {

    private static final Logger logger = LoggerFactory.getLogger(Monitor.class);

    @Value("${path.in}")
    private String inPath;

    private final FileDataService fileDataService;

    public Monitor(FileDataService fileDataService) {
        this.fileDataService = fileDataService;
    }

    @Scheduled(fixedRateString = "${monitor.fixed-rate}")
    public void monitor() {
        try {
            logger.info("Monitoring folder: {}", inPath);
            Path folderPath = FileHelper.getPath(inPath);
            List<Path> folderContent = FileHelper.walk(folderPath);
            folderContent.stream().filter(FileHelper::isFile).forEach(fileDataService::process);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error to monitoring folder");
            throw new FailMonitorException();
        }
    }
}