package ca.jbrains.pos;

import java.util.Map;

public class Catalog {
    private final Map<String, String> pricesByBarcode;

    public Catalog(Map<String, String> pricesByBarcode) {
        this.pricesByBarcode = pricesByBarcode;
    }

    String findPrice(String barcode) {
        return pricesByBarcode.get(barcode);
    }
}
