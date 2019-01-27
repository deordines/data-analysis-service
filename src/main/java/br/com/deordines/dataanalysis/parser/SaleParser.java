package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.dto.Item;
import br.com.deordines.dataanalysis.dto.Sale;

import java.util.List;
import java.util.StringTokenizer;

public class SaleParser extends AParser {

    public static Sale parse(String line) {
        StringTokenizer tokenizer = splitDefault(line);
        if (tokenizer.countTokens() > 3)
            tokenizer.nextToken();
        Long id = Long.valueOf(tokenizer.nextToken());
        List<Item> items = ItemParser.parse(tokenizer.nextToken());
        String salesman = tokenizer.nextToken();
        return new Sale(id, items, salesman);
    }
}
