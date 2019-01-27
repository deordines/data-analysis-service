package br.com.deordines.dataanalysis.stubs;

import br.com.deordines.dataanalysis.dto.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class ItemStub {

    public static Item item1() {
        return new Item(1L, 10, BigDecimal.valueOf(10.10).setScale(2, RoundingMode.CEILING));
    }

    public static Item item2() {
        return new Item(2L, 20, BigDecimal.valueOf(20.99).setScale(2, RoundingMode.CEILING));
    }

    public static Item item3() {
        return new Item(3L, 30, BigDecimal.valueOf(0.30).setScale(2, RoundingMode.CEILING));
    }

    public static List<Item> items1() {
        return Collections.singletonList(item1());
    }

    public static List<Item> items2() {
        return Arrays.asList(item1(), item2());
    }

    public static List<Item> items3() {
        return Arrays.asList(item1(), item2(), item3());
    }
}
