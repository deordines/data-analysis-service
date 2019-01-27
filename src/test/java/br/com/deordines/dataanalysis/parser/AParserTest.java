package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.stubs.LineStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.StringTokenizer;

@RunWith(MockitoJUnitRunner.class)
public class AParserTest {

    @Mock
    private AParser aParser;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(AParser.class, "DEFAULT_CHARACTER", "ç");
        ReflectionTestUtils.setField(AParser.class, "ITEMS_CHARACTER", ",");
        ReflectionTestUtils.setField(AParser.class, "ITEM_CHARACTER", "-");
    }

    @Test
    public void should_returnTokenizerWithFourTokens_when_givenSalesmanLine() {
        String methodToExecute = "splitDefault";
        String argument = LineStub.salesman1();
        StringTokenizer tested = ReflectionTestUtils.invokeMethod(aParser, methodToExecute, argument);
        Assert.assertEquals(4, tested.countTokens());
    }

    @Test
    public void should_returnTokenizerWithFourTokens_when_givenClientLine() {
        String methodToExecute = "splitDefault";
        String argument = LineStub.client1();
        StringTokenizer tested = ReflectionTestUtils.invokeMethod(aParser, methodToExecute, argument);
        Assert.assertEquals(4, tested.countTokens());
    }

    @Test
    public void should_returnTokenizerWithThreeTokens_when_givenItemsLine() {
        String methodToExecute = "splitItems";
        String argument = LineStub.item1();
        StringTokenizer tested = ReflectionTestUtils.invokeMethod(aParser, methodToExecute, argument);
        Assert.assertEquals(1, tested.countTokens());
    }

    @Test
    public void should_returnTokenizerWithThreeTokens_when_givenItemLine() {
        String methodToExecute = "splitItem";
        String argument = LineStub.item1();
        StringTokenizer tested = ReflectionTestUtils.invokeMethod(aParser, methodToExecute, argument);
        Assert.assertEquals(3, tested.countTokens());
    }

    @Test
    public void should_formatLineWithLineSeparator_when_givenLineAndCharacter() {
        String methodToExecute = "format";
        Object[] arguments = {LineStub.salesman1(), "ç"};
        String tested = ReflectionTestUtils.invokeMethod(aParser, methodToExecute, arguments);
        Assert.assertNotNull(tested);
        Assert.assertEquals(4, tested.split(System.getProperty("line.separator")).length);
    }
}
