package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.dto.Salesman;
import br.com.deordines.dataanalysis.stubs.LineStub;
import br.com.deordines.dataanalysis.stubs.SalesmanStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.NoSuchElementException;

@RunWith(MockitoJUnitRunner.class)
public class SalesmanParserTest {

    @Before
    public void setup() {
        ReflectionTestUtils.setField(AParser.class, "DEFAULT_CHARACTER", "ç");
    }

    @Test
    public void should_returnSalesman_when_givenValidLine() {
        String line = LineStub.salesman1();
        Salesman tested = SalesmanParser.parse(line);
        Salesman expectateValue = SalesmanStub.salesman1();
        Assert.assertEquals(expectateValue.toString(), tested.toString());
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throwNoSuchElementException_when_givenInvalidLine() {
        String line = "any_data";
        SalesmanParser.parse(line);
    }
}
