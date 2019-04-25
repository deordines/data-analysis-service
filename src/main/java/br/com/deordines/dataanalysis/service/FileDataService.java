package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.config.DirectoryPathConfig;
import br.com.deordines.dataanalysis.dto.Client;
import br.com.deordines.dataanalysis.dto.FileData;
import br.com.deordines.dataanalysis.dto.Sale;
import br.com.deordines.dataanalysis.dto.Salesman;
import br.com.deordines.dataanalysis.exception.FailProcessFileException;
import br.com.deordines.dataanalysis.exception.InvalidFileException;
import br.com.deordines.dataanalysis.parser.ClientParser;
import br.com.deordines.dataanalysis.parser.SaleParser;
import br.com.deordines.dataanalysis.parser.SalesmanParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class FileDataService {

    private static final Logger logger = LoggerFactory.getLogger(FileDataService.class);

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private final FileService fileService;
    private final ReportDataService reportDataService;

    public FileDataService(FileService fileService, ReportDataService reportDataService) {
        this.fileService = fileService;
        this.reportDataService = reportDataService;
    }

    public void process(Path filePath) {
        try {
            logger.info("Starting file processing");
            filePath = fileService.move(filePath, DirectoryPathConfig.getPROCESSING());
            String filename = fileService.getFilename(filePath);
            logger.info("File: {}", filename);

            if (!fileService.isSupportedFile(filename))
                throw new InvalidFileException();

            FileData fileData = extractFileData(fileService.read(filePath));
            reportDataService.buildReport(fileData, filename);
            fileService.move(filePath, DirectoryPathConfig.getPROCESSED());
        } catch (InvalidFileException e) {
            fileService.move(filePath, DirectoryPathConfig.getERROR());
            throw e;
        } catch (Exception e) {
            logger.error("Error to processing file.", e);
            fileService.move(filePath, DirectoryPathConfig.getERROR());
            throw new FailProcessFileException();
        }
    }

    private FileData extractFileData(byte[] data) {
        logger.info("File data extraction.");
        String fileData = new String(data, StandardCharsets.UTF_8);
        List<String> lines = Arrays.asList(fileData.split(LINE_SEPARATOR));

        List<Salesman> salesmans = new SalesmanParser(lines).parse();
        List<Client> clients = new ClientParser(lines).parse();
        List<Sale> sales = new SaleParser(lines).parse();

        return FileData.Builder.of()
                .salesmans(salesmans)
                .clients(clients)
                .sales(sales)
                .build();
    }
}
