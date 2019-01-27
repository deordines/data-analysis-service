package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.dto.FileData;
import br.com.deordines.dataanalysis.dto.Item;
import br.com.deordines.dataanalysis.dto.ReportData;
import br.com.deordines.dataanalysis.dto.Sale;
import br.com.deordines.dataanalysis.exception.FailReportProcessException;
import br.com.deordines.dataanalysis.helper.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportDataService {

    private static final Logger logger = LoggerFactory.getLogger(ReportDataService.class);

    private static final String OUTPUT_FILENAME = "flat_%s.done.dat";
    private static final String OUTPUT_PATTERN_A = "%-30s%d%n";
    private static final String OUTPUT_PATTERN_B = "%-30s%s";

    @Value("${path.out}")
    private String outPath;

    public ReportData report(FileData fileData, String filename) {
        try {
            logger.info("Starting data reporting.");
            String filenameOutput = String.format(OUTPUT_FILENAME, filename);
            ReportData reportData = new ReportData(
                    fileData.getClients().size(),
                    fileData.getSalesmans().size(),
                    getSaleMoreExpensive(fileData.getSales()).getId(),
                    getWorstSalesman(fileData.getSales())
            );
            byte[] data = buildReportDataOutput(reportData);
            FileHelper.createFile(filenameOutput, data, outPath);
            return reportData;
        } catch (Exception e) {
            throw new FailReportProcessException();
        }
    }

    private Sale getSaleMoreExpensive(List<Sale> sales) {
        logger.info("Getting sale more expensive.");
        return sales.stream().max(Comparator.comparing(this::getTotalValue)).orElse(null);
    }

    private String getWorstSalesman(List<Sale> sales) {
        logger.info("Getting worst salesman.");
        return sales.stream()
                .collect(Collectors.groupingBy(Sale::getSalesman,
                        Collectors.reducing(BigDecimal.ZERO, this::getTotalValue, BigDecimal::add)))
                .entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .get().getKey();
    }

    private BigDecimal getTotalValue(Sale sale) {
        return sale.getItems().stream().map(this::getTotalValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getTotalValue(Item item) {
        return item.getPrice().multiply(new BigDecimal(item.getQuantity()));
    }

    private byte[] buildReportDataOutput(ReportData data) {
        String clientsAmount = buildOutputPatternA("Clients Amount", data.getClientsAmount());
        String salesmanAmount = buildOutputPatternA("Salesman Amount", data.getSalesmansAmount());
        String saleIdMoreExpensive = buildOutputPatternA("Sale ID More Expensive", data.getIdSaleMoreExpensive());
        String worstSalesman = buildOutputPatternB("Worst Salesman", data.getWorstSalesman());
        String dataReport = clientsAmount + salesmanAmount + saleIdMoreExpensive + worstSalesman;
        return dataReport.getBytes();
    }

    private String buildOutputPatternA(String description, Object data) {
        return String.format(OUTPUT_PATTERN_A, description, data);
    }

    private String buildOutputPatternB(String description, String data) {
        return String.format(OUTPUT_PATTERN_B, description, data);
    }
}
