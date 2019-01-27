package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.stubs.LineStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class LayoutParserTest {

    @InjectMocks
    private LayoutParser layoutParser;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(AParser.class, "DEFAULT_CHARACTER", "ç");
    }

    @Test
    public void should_returnOne_when_givenSalesmanLine() {
        String line = LineStub.salesman1();
        Long tested = LayoutParser.parse(line);
        Assert.assertEquals(Long.valueOf(1), tested);
    }

    @Test
    public void should_returnTwo_when_givenClientLine() {
        String line = LineStub.client1();
        Long tested = LayoutParser.parse(line);
        Assert.assertEquals(Long.valueOf(2), tested);
    }

    @Test
    public void should_returnThree_when_givenSaleLine() {
        String line = LineStub.sale1();
        Long tested = LayoutParser.parse(line);
        Assert.assertEquals(Long.valueOf(3), tested);
    }

    @Test
    public void should_returnOne_when_givenLine() {
        String line = "1çValueçValue";
        Long tested = LayoutParser.parse(line);
        Assert.assertEquals(Long.valueOf(1), tested);
    }
}
