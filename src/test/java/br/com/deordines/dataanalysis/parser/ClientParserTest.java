package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;
import br.com.deordines.dataanalysis.dto.Client;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ParserCharacterConfig.class)
public class ClientParserTest {

    @Test
    public void shouldReturnClientListWhenParseLines() {
        mockStatic(ParserCharacterConfig.class);
        when(ParserCharacterConfig.getSTANDARD()).thenReturn("ç");
        IParse<Client> clientParser = new ClientParser(linesStub());
        List<Client> clients = clientParser.parse();
        Assert.assertEquals(2, clients.size());
        Assert.assertEquals("Client 1", clients.get(0).getName());
        Assert.assertEquals("Client 2", clients.get(1).getName());
    }

    private List<String> linesStub() {
        return Arrays.asList(
                "001ç12345678901çSalesman 1ç10000",
                "001ç12345678902çSalesman 2ç10000.99",
                "002ç12345678000101çClient 1çBusiness Area 1",
                "002ç12345678000102çClient 2çBusiness Area 2",
                "003ç1ç[1-10-10.10]çSalesman 1",
                "003ç2ç[1-10-10.10,2-20-20.99]çSalesman 2",
                "003ç3ç[1-10-10.10,2-20-20.99,3-30-0.30]çSalesman 1");
    }
}
