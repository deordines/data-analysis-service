package br.com.deordines.dataanalysis.monitor;

import br.com.deordines.dataanalysis.service.FileDataService;
import br.com.deordines.dataanalysis.service.FileService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MonitorTest {

    private Monitor monitor;
    private FileService fileService;
    private FileDataService fileDataService;

    @Before
    public void setUp() {
        fileService = mock(FileService.class);
        fileDataService = mock(FileDataService.class);
        monitor = new Monitor(fileService, fileDataService);
    }

    @Test
    public void shouldProcessingFileWhenPathIsFile() {
        when(fileService.getPath(any())).thenReturn(mock(Path.class));
        when(fileService.walk(any())).thenReturn(Collections.singletonList(mock(Path.class)));
        when(fileService.isFile(any())).thenReturn(Boolean.TRUE);
        monitor.monitor();
    }

    @Test
    public void shouldDoNothingWhenPathIsDirectory() {
        when(fileService.getPath(any())).thenReturn(mock(Path.class));
        when(fileService.walk(any())).thenReturn(Collections.singletonList(mock(Path.class)));
        when(fileService.isFile(any())).thenReturn(Boolean.FALSE);
        monitor.monitor();
    }
}
