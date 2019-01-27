package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.dto.Item;
import br.com.deordines.dataanalysis.stubs.ItemStub;
import br.com.deordines.dataanalysis.stubs.LineStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class ItemParserTest {

    @Before
    public void setup() {
        ReflectionTestUtils.setField(AParser.class, "ITEMS_CHARACTER", ",");
        ReflectionTestUtils.setField(AParser.class, "ITEM_CHARACTER", "-");
    }

    @Test
    public void should_returnItems_when_givenValidLine() {
        String line = LineStub.sale1();
        List<Item> tested = ItemParser.parse(line);
        List<Item> expectateValue = ItemStub.items1();
        Assert.assertEquals(expectateValue.toString(), tested.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void should_throwNumberFormatException_when_givenInvalidLine() {
        String line = "any_data";
        ItemParser.parse(line);
    }

    @Test
    public void should_returnItem_when_givenValidItem() {
        String methodToExecute = "item";
        String argument = LineStub.item1();
        Item tested = ReflectionTestUtils.invokeMethod(mock(ItemParser.class), methodToExecute, argument);
        Item expectateValue = ItemStub.item1();
        Assert.assertEquals(expectateValue.toString(), tested.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void should_throwNumberFormatException_when_givenInvalidItem() {
        String methodToExecute = "item";
        String argument = "any_data";
        ReflectionTestUtils.invokeMethod(mock(ItemParser.class), methodToExecute, argument);
    }
}
