package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ItemParser extends AParser {

    private static final String ITEMS_REGEX = "(.*)\\[(.*)](.*)";
    private static final String ITEMS_REPLACEMENT = "$2";

    public static List<Item> parse(String line) {
        line = line.replaceAll(ITEMS_REGEX, ITEMS_REPLACEMENT);
        StringTokenizer tokenizer = splitItems(line);
        List<Item> items = new ArrayList<>();
        while (tokenizer.hasMoreTokens())
            items.add(item(tokenizer.nextToken()));
        return items;
    }

    private static Item item(String item) {
        StringTokenizer tokenizer = splitItem(item);
        Long id = Long.valueOf(tokenizer.nextToken());
        Integer quantity = Integer.valueOf(tokenizer.nextToken());
        BigDecimal price = new BigDecimal(tokenizer.nextToken())
                .setScale(2, RoundingMode.CEILING);
        return new Item(id, quantity, price);
    }
}
