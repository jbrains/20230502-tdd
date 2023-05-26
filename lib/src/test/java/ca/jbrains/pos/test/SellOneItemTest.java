package ca.jbrains.pos.test;

import ca.jbrains.pos.Barcode;
import ca.jbrains.pos.Catalog;
import ca.jbrains.pos.EnglishLanguageMessageFormat;
import ca.jbrains.pos.Sale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;

public class SellOneItemTest {
    @Test
    void productFound() {
        final EnglishLanguageMessageFormat englishLanguageMessageFormat = new EnglishLanguageMessageFormat();
        final Sale sale = new Sale(englishLanguageMessageFormat, new Catalog(new HashMap<String, String>() {{
            put("12345", "CAD 7.95");
            put("23456", "CAD 12.50");
        }}));

        Assertions.assertEquals("CAD 7.95", sale.onBarcode(Barcode.parseBarcode("12345")));
    }

    @Test
    void anotherProductFound() {
        final EnglishLanguageMessageFormat englishLanguageMessageFormat = new EnglishLanguageMessageFormat();
        final Sale sale = new Sale(englishLanguageMessageFormat, new Catalog(new HashMap<String, String>() {{
            put("12345", "CAD 7.95");
            put("23456", "CAD 12.50");
        }}));

        Assertions.assertEquals("CAD 12.50", sale.onBarcode(Barcode.parseBarcode("23456")));
    }

    @Test
    void productNotFound() {
        final EnglishLanguageMessageFormat englishLanguageMessageFormat = new EnglishLanguageMessageFormat();
        final Sale sale = new Sale(englishLanguageMessageFormat, new Catalog(Collections.emptyMap()));

        Assertions.assertEquals("Product not found: 99999", sale.onBarcode(Barcode.parseBarcode("99999")));
    }
}
