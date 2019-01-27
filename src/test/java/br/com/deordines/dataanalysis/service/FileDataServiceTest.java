package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.dto.FileData;
import br.com.deordines.dataanalysis.dto.FormatType;
import br.com.deordines.dataanalysis.exception.FailProcessFileException;
import br.com.deordines.dataanalysis.exception.InvalidFileException;
import br.com.deordines.dataanalysis.exception.MoveFileException;
import br.com.deordines.dataanalysis.exception.ReadFileException;
import br.com.deordines.dataanalysis.helper.FileHelper;
import br.com.deordines.dataanalysis.parser.AParser;
import br.com.deordines.dataanalysis.stubs.FileDataStub;
import br.com.deordines.dataanalysis.stubs.LineStub;
import br.com.deordines.dataanalysis.stubs.ReportDataStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.Path;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileHelper.class)
public class FileDataServiceTest {

    @InjectMocks
    private FileDataService fileDataService;

    @Mock
    private FormatType formatType;

    @Mock
    private ReportDataService reportDataService;

    @Before
    public void setup() {
        mockStatic(FileHelper.class);
        FormatType formatType = new FormatType();
        formatType.setExtensions(Collections.singletonList("DAT"));
        ReflectionTestUtils.setField(fileDataService, "formatType", formatType);
        ReflectionTestUtils.setField(fileDataService, "processedPath", "processedPath");
        ReflectionTestUtils.setField(fileDataService, "errorPath", "errorPath");
        ReflectionTestUtils.setField(AParser.class, "DEFAULT_CHARACTER", "ç");
        ReflectionTestUtils.setField(AParser.class, "ITEMS_CHARACTER", ",");
        ReflectionTestUtils.setField(AParser.class, "ITEM_CHARACTER", "-");
    }

    @Test
    public void should_returnFileData() {
        when(FileHelper.getFilename(any())).thenReturn("filename.DAT");
        when(reportDataService.report(any(), any())).thenReturn(ReportDataStub.reportData());
        when(FileHelper.read(any())).thenReturn(LineStub.fileData());
        FileData tested = fileDataService.process(mock(Path.class));
        FileData expected = FileDataStub.fileData();
        Assert.assertNotNull(expected.toString(), tested.toString());
    }

    @Test(expected = InvalidFileException.class)
    public void should_throwInvalidFileException_when_givenInvalidFile() {
        when(FileHelper.getFilename(any())).thenReturn("filename.TXT");
        fileDataService.process(mock(Path.class));
    }

    @Test(expected = MoveFileException.class)
    public void should_throwMoveFileException_when_fileHelperThrowsException() {
        when(FileHelper.getFilename(any())).thenReturn("filename.DAT");
        when(FileHelper.move(any(), any())).thenThrow(MoveFileException.class);
        fileDataService.process(mock(Path.class));
    }

    @Test(expected = ReadFileException.class)
    public void should_throwReadFileException_when_fileHelperThrowsException() {
        when(FileHelper.getFilename(any())).thenReturn("filename.DAT");
        when(FileHelper.read(any())).thenThrow(ReadFileException.class);
        fileDataService.process(mock(Path.class));
    }

    @Test(expected = FailProcessFileException.class)
    public void should_throwFailProcessFileException_when_processThrowsException() {
        fileDataService.process(mock(Path.class));
    }

    @Test(expected = FailProcessFileException.class)
    public void should_throwFailProcessFileException_when_extractDataThrowsException() {
        when(FileHelper.getFilename(any())).thenReturn("filename.DAT");
        when(FileHelper.read(any())).thenReturn("wrong data".getBytes());
        fileDataService.process(mock(Path.class));
    }

    @Test
    public void should_extractFileData_when_givenFileData() {
        String methodToExecute = "extractFileData";
        byte[] argument = LineStub.fileData();
        FileData tested = ReflectionTestUtils.invokeMethod(fileDataService, methodToExecute, argument);
        FileData expectateValue = FileDataStub.fileData();
        Assert.assertEquals(expectateValue.toString(), tested.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throwsInvalidFileException_when_givenEmptyFile() {
        String methodToExecute = "extractFileData";
        byte[] argument = "".getBytes();
        ReflectionTestUtils.invokeMethod(fileDataService, methodToExecute, argument);
    }

    @Test
    public void should_returnTrue_when_givenValidFormatType() {
        when(formatType.getExtensions()).thenReturn(Collections.singletonList("DAT"));
        String methodToExecute = "isValidFormatType";
        String argument = "fileData.dat";
        Boolean tested = ReflectionTestUtils.invokeMethod(fileDataService, methodToExecute, argument);
        Assert.assertTrue(tested);
    }

    @Test
    public void should_returnFalse_when_givenInvalidFormatType() {
        String methodToExecute = "isValidFormatType";
        String argument = "fileData.txt";
        Boolean tested = ReflectionTestUtils.invokeMethod(fileDataService, methodToExecute, argument);
        Assert.assertFalse(tested);
    }
}
