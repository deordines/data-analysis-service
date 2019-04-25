package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;
import br.com.deordines.dataanalysis.exception.FailProcessFileException;
import br.com.deordines.dataanalysis.exception.InvalidFileException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ParserCharacterConfig.class)
public class FileDataServiceTest {

    private FileDataService fileDataService;
    private FileService fileService;
    private ReportDataService reportDataService;

    @Before
    public void setUp() {
        fileService = mock(FileService.class);
        reportDataService = mock(ReportDataService.class);
        fileDataService = new FileDataService(fileService, reportDataService);
        mockStatic(ParserCharacterConfig.class);
    }

    @Test
    public void shouldProcessPathWhenFileIsSupportedAndContentIsValid() {
        when(fileService.move(any(), any())).thenReturn(mock(Path.class));
        when(fileService.getFilename(any())).thenReturn("filename.dat");
        when(fileService.isSupportedFile(any())).thenReturn(Boolean.TRUE);
        when(fileService.read(any())).thenReturn(fileDataStub());
        when(ParserCharacterConfig.getSTANDARD()).thenReturn("ç");
        when(ParserCharacterConfig.getITEMS()).thenReturn(",");
        when(ParserCharacterConfig.getITEM()).thenReturn("-");
        fileDataService.process(mock(Path.class));
    }

    @Test(expected = InvalidFileException.class)
    public void shouldThrowInvalidFileExceptionWhenFileIsNotSupported() {
        when(fileService.move(any(), any())).thenReturn(mock(Path.class));
        when(fileService.getFilename(any())).thenReturn("filename.txt");
        when(fileService.isSupportedFile(any())).thenReturn(Boolean.FALSE);
        fileDataService.process(mock(Path.class));
    }

    @Test(expected = FailProcessFileException.class)
    public void shouldThrowFailProcessFileExceptionWhenFileContentIsInvalid() {
        when(fileService.move(any(), any())).thenReturn(mock(Path.class));
        when(fileService.getFilename(any())).thenReturn("filename.dat");
        when(fileService.isSupportedFile(any())).thenReturn(Boolean.TRUE);
        when(fileService.read(any())).thenReturn("invalid content".getBytes());
        when(ParserCharacterConfig.getSTANDARD()).thenReturn("ç");
        when(ParserCharacterConfig.getITEMS()).thenReturn(",");
        when(ParserCharacterConfig.getITEM()).thenReturn("-");
        fileDataService.process(mock(Path.class));
    }

    private byte[] fileDataStub() {
        StringBuilder stringBuilder = new StringBuilder()
                .append("001ç12345678901çSalesman 1ç10000")
                .append("001ç12345678902çSalesman 2ç10000.99")
                .append("002ç12345678000101çClient 1çBusiness Area 1")
                .append("002ç12345678000102çClient 2çBusiness Area 2")
                .append("003ç1ç[1-10-10.10]çSalesman 1")
                .append("003ç2ç[1-10-10.10,2-20-20.99]çSalesman 2")
                .append("003ç3ç[1-10-10.10,2-20-20.99,3-30-0.30]çSalesman 1");
        return stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}
