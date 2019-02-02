package br.com.deordines.dataanalysis.parser;

import br.com.deordines.dataanalysis.config.ParserCharacterConfig;
import br.com.deordines.dataanalysis.dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class ItemParser extends AParser<Item> {

    public ItemParser(List<String> lines) {
        super(lines);
    }

    @Override
    public List<Item> parse() {
        return lines.parallelStream().map(x -> {
            StringTokenizer tokenizer = split(x, ParserCharacterConfig.getITEM());
            Long id = Long.valueOf(tokenizer.nextToken());
            Integer quantity = Integer.valueOf(tokenizer.nextToken());
            BigDecimal price = new BigDecimal(tokenizer.nextToken())
                    .setScale(2, RoundingMode.CEILING);
            return new Item(id, quantity, price);
        }).collect(Collectors.toList());
    }
}
