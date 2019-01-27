package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.dto.Sale;
import br.com.deordines.dataanalysis.stubs.LineStub;
import br.com.deordines.dataanalysis.stubs.SaleStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class SaleParserTest {

    @Before
    public void setup() {
        ReflectionTestUtils.setField(AParser.class, "DEFAULT_CHARACTER", "ç");
    }

    @Test
    public void should_returnSale_when_givenValidLine() {
        String line = LineStub.sale1();
        Sale tested = SaleParser.parse(line);
        Sale expectateValue = SaleStub.sale1();
        Assert.assertEquals(expectateValue.toString(), tested.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void should_throwNumberFormatException_when_givenInvalidLine() {
        String line = "any_data";
        SaleParser.parse(line);
    }
}