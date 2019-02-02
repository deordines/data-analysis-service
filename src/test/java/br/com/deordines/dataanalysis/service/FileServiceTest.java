package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.config.SupportedFileConfig;
import br.com.deordines.dataanalysis.exception.MoveFileException;
import br.com.deordines.dataanalysis.exception.ReadFileException;
import br.com.deordines.dataanalysis.exception.WalkFileException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SupportedFileConfig.class, Files.class})
public class FileServiceTest {

    private FileService fileService;

    @Before
    public void setUp() {
        fileService = new FileService();
        mockStatic(SupportedFileConfig.class);
    }
    
    @Test(expected = ReadFileException.class)
    public void shouldThrowReadFileExceptionWhenReadMockedPath() {
        fileService.read(mock(Path.class));
    }

    @Test(expected = MoveFileException.class)
    public void shouldThrowMoveFileExceptionWhenMoveMockedPath() {
        fileService.move(mock(Path.class), "target");
    }

    @Test(expected = WalkFileException.class)
    public void shouldThrowWalkFileExceptionWhenWalkInMockedPath() {
        fileService.walk(mock(Path.class));
    }


    @Test
    public void shouldReturnTrueWhenIsSupportedFile() {
        when(SupportedFileConfig.getEXTENSIONS()).thenReturn(Collections.singletonList("dat"));
        Boolean supportedFile = fileService.isSupportedFile("filename.dat");
        Assert.assertTrue(supportedFile);
    }

    @Test
    public void shouldReturnFalseWhenIsNotSupportedFile() {
        when(SupportedFileConfig.getEXTENSIONS()).thenReturn(Collections.singletonList("dat"));
        Boolean supportedFile = fileService.isSupportedFile("filename.txt");
        Assert.assertFalse(supportedFile);
    }

    @Test
    public void shouldReturnFilenameWithoutExtension() {
        String filename = fileService.removeExtensionFromFilename("filename.dat");
        Assert.assertEquals("filename", filename);
    }
}