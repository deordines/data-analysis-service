package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;
import br.com.deordines.dataanalysis.dto.Item;
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
public class ItemParserTest {

    @Test
    public void shouldReturnItemListWhenParseLines() {
        mockStatic(ParserCharacterConfig.class);
        when(ParserCharacterConfig.getITEM()).thenReturn("-");
        IParse<Item> itemParser = new ItemParser(itemsStub());
        List<Item> items = itemParser.parse();
        Assert.assertEquals(3, items.size());
        Assert.assertEquals(Long.valueOf(1), items.get(0).getId());
        Assert.assertEquals(Long.valueOf(2), items.get(1).getId());
        Assert.assertEquals(Long.valueOf(3), items.get(2).getId());
    }

    private List<String> itemsStub() {
        return Arrays.asList("1-10-10.10", "2-20-20.99", "3-30-0.30");
    }
}
