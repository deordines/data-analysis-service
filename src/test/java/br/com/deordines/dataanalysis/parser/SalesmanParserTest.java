package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;
import br.com.deordines.dataanalysis.dto.Salesman;
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
public class SalesmanParserTest {

    @Test
    public void shouldReturnSalesmanWhenParseLines() {
        mockStatic(ParserCharacterConfig.class);
        when(ParserCharacterConfig.getSTANDARD()).thenReturn("ç");
        IParse<Salesman> salesmanParser = new SalesmanParser(linesStub());
        List<Salesman> salesmans = salesmanParser.parse();
        Assert.assertEquals(2, salesmans.size());
        Assert.assertEquals("Salesman 1", salesmans.get(0).getName());
        Assert.assertEquals("Salesman 2", salesmans.get(1).getName());
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
