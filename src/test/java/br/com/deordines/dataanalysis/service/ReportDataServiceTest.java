package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.dto.Client;
import br.com.deordines.dataanalysis.dto.FileData;
import br.com.deordines.dataanalysis.dto.Item;
import br.com.deordines.dataanalysis.dto.Sale;
import br.com.deordines.dataanalysis.dto.Salesman;
import br.com.deordines.dataanalysis.exception.FailReportException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportDataServiceTest {

    private ReportDataService reportDataService;
    private FileService fileService;

    @Before
    public void setUp() {
        fileService = mock(FileService.class);
        reportDataService = new ReportDataService(fileService);
    }

    @Test
    public void shouldCreateReportWithValidFileDataAndValidFilename() {
        when(fileService.removeExtensionFromFilename(any())).thenReturn("filename");
        when(fileService.createFile(any(), any(), any())).thenReturn(mock(Path.class));
        reportDataService.buildReport(fileDataStub(), "filename.dat");
    }

    @Test(expected = FailReportException.class)
    public void shouldThrowFailReportExceptionWithInvalidFileData() {
        when(fileService.removeExtensionFromFilename(any())).thenReturn("filename");
        reportDataService.buildReport(null, "filename.dat");
    }

    @Test(expected = FailReportException.class)
    public void shouldThrowFailReportExceptionWithEmptySales() {
        when(fileService.removeExtensionFromFilename(any())).thenReturn("filename");
        FileData fileData = fileDataStub();
        fileData.setSales(Collections.emptyList());
        reportDataService.buildReport(fileData, "filename.dat");
    }

    private FileData fileDataStub() {
        return FileData.Builder.of()
                .salesmans(Collections.singletonList(salesmanStub()))
                .clients(Collections.singletonList(clientStub()))
                .sales(Collections.singletonList(saleStub()))
                .build();
    }

    private Salesman salesmanStub() {
        return new Salesman("taxId", "name", BigDecimal.TEN);
    }

    private Client clientStub() {
        return new Client("taxId", "name", "businessArea");
    }

    private Sale saleStub() {
        return new Sale(1L, Collections.singletonList(new Item(1L, 10, BigDecimal.TEN)), "salesman");
    }
}