package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.dto.FileData;
import br.com.deordines.dataanalysis.dto.FormatType;
import br.com.deordines.dataanalysis.exception.CustomException;
import br.com.deordines.dataanalysis.exception.FailProcessFileException;
import br.com.deordines.dataanalysis.exception.InvalidFileException;
import br.com.deordines.dataanalysis.helper.FileHelper;
import br.com.deordines.dataanalysis.parser.ClientParser;
import br.com.deordines.dataanalysis.parser.LayoutParser;
import br.com.deordines.dataanalysis.parser.SaleParser;
import br.com.deordines.dataanalysis.parser.SalesmanParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class FileDataService {

    private static final Logger logger = LoggerFactory.getLogger(FileDataService.class);

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String FILENAME_REGEX = "(^.*)([.+$].*)";
    private static final String FILENAME_REPLACEMENT = "$1";
    private static final String EXTENSION_REPLACEMENT = "$2";

    @Value("${path.processed}")
    private String processedPath;

    @Value("${path.error}")
    private String errorPath;

    private final FormatType formatType;
    private final ReportDataService reportDataService;

    public FileDataService(FormatType formatType, ReportDataService reportDataService) {
        this.formatType = formatType;
        this.reportDataService = reportDataService;
    }

    public FileData process(Path filePath) {
        try {
            logger.info("Starting file processing.");
            if (!isValidFormatType(FileHelper.getFilename(filePath)))
                throw new InvalidFileException();

            FileData fileData = extractFileData(FileHelper.read(filePath));
            String filename = FileHelper.getFilename(filePath).replaceAll(FILENAME_REGEX, FILENAME_REPLACEMENT);
            reportDataService.report(fileData, filename);
            FileHelper.move(filePath, processedPath);

            return fileData;
        } catch (CustomException e) {
            FileHelper.move(filePath, errorPath);
            throw e;
        } catch (Exception e) {
            logger.error("Error to processing file.", e);
            FileHelper.move(filePath, errorPath);
            throw new FailProcessFileException();
        }
    }

    private FileData extractFileData(byte[] data) {
        logger.info("File data extraction.");
        String fileData = new String(data, StandardCharsets.ISO_8859_1);
        FileData.Builder fileDataBuilder = FileData.Builder.of();
        List<String> lines = Arrays.asList(fileData.split(LINE_SEPARATOR));
        lines.forEach(x -> {
            Long lineType = LayoutParser.parse(x);
            if (lineType == 1)
                fileDataBuilder.salesman(SalesmanParser.parse(x));
            else if (lineType == 2)
                fileDataBuilder.client(ClientParser.parse(x));
            else
                fileDataBuilder.sale(SaleParser.parse(x));
        });
        return fileDataBuilder.build();
    }

    private Boolean isValidFormatType(String filename) {
        logger.info("Format type validation.");
        return formatType.getExtensions().stream()
                .anyMatch(x -> filename.replaceAll(FILENAME_REGEX, EXTENSION_REPLACEMENT).toUpperCase()
                        .contains(x.toUpperCase()));
    }
}
