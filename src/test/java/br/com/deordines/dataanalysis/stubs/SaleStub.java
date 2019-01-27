package br.com.deordines.dataanalysis.stubs;

import br.com.deordines.dataanalysis.dto.Sale;

import java.util.Arrays;
import java.util.List;

public final class SaleStub {

    public static Sale sale1() {
        return new Sale(1L, ItemStub.items1(), "Salesman 1");
    }

    public static Sale sale2() {
        return new Sale(2L, ItemStub.items2(), "Salesman 2");
    }

    public static Sale sale3() {
        return new Sale(3L, ItemStub.items3(), "Salesman 1");
    }

    public static List<Sale> sales() {
        return Arrays.asList(sale1(), sale2(), sale3());
    }
}