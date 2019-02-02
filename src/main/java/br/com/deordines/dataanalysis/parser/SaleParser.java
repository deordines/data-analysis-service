package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;
import br.com.deordines.dataanalysis.dto.Item;
import br.com.deordines.dataanalysis.dto.Sale;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class SaleParser extends AParser<Sale> {

    private static final Long ID_LAYOUT = 3L;
    private static final String ITEMS_REGEX = "(.*)\\[(.*)](.*)";
    private static final String ITEMS_REPLACEMENT = "$2";

    public SaleParser(List<String> lines) {
        super(ID_LAYOUT, lines);
    }

    @Override
    public List<Sale> parse() {
        return lines.parallelStream().map(x -> {
            StringTokenizer tokenizer = split(x, ParserCharacterConfig.getSTANDARD());
            if (tokenizer.countTokens() > 3)
                tokenizer.nextToken();
            Long id = Long.valueOf(tokenizer.nextToken());
            List<Item> items = new ItemParser(splittedItems(tokenizer.nextToken())).parse();
            String salesman = tokenizer.nextToken();
            return new Sale(id, items, salesman);
        }).collect(Collectors.toList());
    }

    private List<String> splittedItems(String line) {
        line = line.replaceAll(ITEMS_REGEX, ITEMS_REPLACEMENT);
        StringTokenizer tokenizer = split(line, ParserCharacterConfig.getITEMS());
        List<String> items = new ArrayList<>();
        while (tokenizer.hasMoreTokens())
            items.add(tokenizer.nextToken());
        return items;
    }
}
