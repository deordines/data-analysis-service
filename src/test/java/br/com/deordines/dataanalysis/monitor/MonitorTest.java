package br.com.deordines.dataanalysis.monitor;

import br.com.deordines.dataanalysis.exception.FailMonitorException;
import br.com.deordines.dataanalysis.exception.FailProcessFileException;
import br.com.deordines.dataanalysis.exception.WalkFileException;
import br.com.deordines.dataanalysis.helper.FileHelper;
import br.com.deordines.dataanalysis.service.FileDataService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileHelper.class)
public class MonitorTest {

    @InjectMocks
    private Monitor monitor;

    @Mock
    private FileDataService fileDataService;

    @Before
    public void setup() {
        mockStatic(FileHelper.class);
        ReflectionTestUtils.setField(monitor, "inPath", "inPath");
    }

    @Test
    public void should_startingProcessing_when_givenFile() {
        when(FileHelper.walk(any())).thenReturn(Collections.singletonList(mock(Path.class)));
        when(FileHelper.isFile(any())).thenReturn(Boolean.TRUE);
        monitor.monitor();
    }

    @Test
    public void should_doNothing_when_givenDirectory() {
        when(FileHelper.walk(any())).thenReturn(Collections.singletonList(mock(Path.class)));
        when(FileHelper.isFile(any())).thenReturn(Boolean.FALSE);
        monitor.monitor();
    }

    @Test(expected = FailMonitorException.class)
    public void should_throwFileMonitorException_when_fileHelperThrowsException() {
        when(FileHelper.getPath(any())).thenThrow(RuntimeException.class);
        monitor.monitor();
    }

    @Test(expected = WalkFileException.class)
    public void should_throwWalkFileException_when_fileHelperThrowsException() {
        when(FileHelper.walk(any())).thenThrow(WalkFileException.class);
        monitor.monitor();
    }

    @Test(expected = FailProcessFileException.class)
    public void should_throwFailProcessFileException_when_processThrowsException() {
        when(FileHelper.walk(any())).thenReturn(Collections.singletonList(mock(Path.class)));
        when(FileHelper.isFile(any())).thenReturn(Boolean.TRUE);
        when(fileDataService.process(any())).thenThrow(FailProcessFileException.class);
        monitor.monitor();
    }
}
