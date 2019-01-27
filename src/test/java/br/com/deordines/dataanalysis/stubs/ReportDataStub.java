package br.com.deordines.dataanalysis.stubs;

import br.com.deordines.dataanalysis.dto.ReportData;

import java.nio.charset.StandardCharsets;

public final class ReportDataStub {

    public static ReportData reportData() {
        return new ReportData(
                2,
                2,
                3L,
                "Salesman 2");
    }

    public static byte[] reportDataOutput() {
        String clientsAmount = String.format("%s%n", "Clients Amount                2");
        String salesmanAmount = String.format("%s%n", "Salesman Amount               2");
        String saleIdMoreExpensive = String.format("%s%n", "Sale ID More Expensive        3");
        String worstSalesman = "Worst Salesman                Salesman 2";
        String recordData = clientsAmount + salesmanAmount + saleIdMoreExpensive + worstSalesman;
        return recordData.getBytes(StandardCharsets.UTF_8);
    }
}
