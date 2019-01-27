package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.dto.ReportData;
import br.com.deordines.dataanalysis.dto.Sale;
import br.com.deordines.dataanalysis.exception.CreateFileException;
import br.com.deordines.dataanalysis.exception.FailReportProcessException;
import br.com.deordines.dataanalysis.helper.FileHelper;
import br.com.deordines.dataanalysis.stubs.FileDataStub;
import br.com.deordines.dataanalysis.stubs.ReportDataStub;
import br.com.deordines.dataanalysis.stubs.SaleStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileHelper.class)
public class ReportDataServiceTest {

    @InjectMocks
    private ReportDataService reportDataService;

    @Before
    public void setup() {
        mockStatic(FileHelper.class);
        ReflectionTestUtils.setField(reportDataService, "outPath", "outPath");
    }

    @Test
    public void should_returnReportData() {
        ReportData tested = reportDataService.report(FileDataStub.fileData(), "filename");
        ReportData expected = ReportDataStub.reportData();
        Assert.assertEquals(expected.toString(), tested.toString());
    }

    @Test(expected = FailReportProcessException.class)
    public void should_throwFailReportProcessException_when_fileHelperThrowsException() {
        when(FileHelper.createFile(any(), any(), any())).thenThrow(CreateFileException.class);
        reportDataService.report(FileDataStub.fileData(), "filename");
    }

    @Test(expected = FailReportProcessException.class)
    public void should_throwFailReportException_when_givenWrongFileData() {
        reportDataService.report(null, "filename");
    }

    @Test
    public void should_returnSaleMoreExpensive_when_givenSalesList() {
        String methodToExecute = "getSaleMoreExpensive";
        List<Sale> argument = Arrays.asList(SaleStub.sale1(), SaleStub.sale2(), SaleStub.sale3());
        Sale tested = ReflectionTestUtils.invokeMethod(reportDataService, methodToExecute, argument);
        Sale expectedValue = SaleStub.sale3();
        Assert.assertEquals(expectedValue.toString(), tested.toString());
    }

    @Test
    public void should_returnWorstSalesman_when_givenSalesList() {
        String methodToExecute = "getWorstSalesman";
        List<Sale> argument = Arrays.asList(SaleStub.sale1(), SaleStub.sale2(), SaleStub.sale3());
        String tested = ReflectionTestUtils.invokeMethod(reportDataService, methodToExecute, argument);
        String expectedValue = "Salesman 2";
        Assert.assertEquals(expectedValue, tested);
    }

    @Test
    public void should_returnEncodedReportDataOutput_when_givenReportData() {
        String methodToExecute = "buildReportDataOutput";
        ReportData argument = ReportDataStub.reportData();
        byte[] tested = ReflectionTestUtils.invokeMethod(reportDataService, methodToExecute, argument);
        String expected = new String(ReportDataStub.reportDataOutput(), StandardCharsets.UTF_8);
        Assert.assertEquals(expected, new String(tested, StandardCharsets.UTF_8));
    }
}
