package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    void productFound() {
        final EnglishLanguageMessageFormat englishLanguageMessageFormat = new EnglishLanguageMessageFormat();
        final Sale sale = new Sale(englishLanguageMessageFormat, new Catalog(new HashMap<String, String>() {{
            put("12345", "CAD 7.95");
            put("23456", "CAD 12.50");
        }}));

        Assertions.assertEquals("CAD 7.95", sale.onBarcode("12345"));
    }

    @Test
    void anotherProductFound() {
        final EnglishLanguageMessageFormat englishLanguageMessageFormat = new EnglishLanguageMessageFormat();
        final Sale sale = new Sale(englishLanguageMessageFormat, new Catalog(new HashMap<String, String>() {{
            put("12345", "CAD 7.95");
            put("23456", "CAD 12.50");
        }}));

        Assertions.assertEquals("CAD 12.50", sale.onBarcode("23456"));
    }

    @Test
    void productNotFound() {
        final EnglishLanguageMessageFormat englishLanguageMessageFormat = new EnglishLanguageMessageFormat();
        final Sale sale = new Sale(englishLanguageMessageFormat, new Catalog(Collections.emptyMap()));

        Assertions.assertEquals("Product not found: 99999", sale.onBarcode("99999"));
    }

    @Test
    void emptyBarcode() {
        final EnglishLanguageMessageFormat englishLanguageMessageFormat = new EnglishLanguageMessageFormat();
        final Sale sale = new Sale(englishLanguageMessageFormat, null);

        Assertions.assertEquals("Scanning error: empty barcode", sale.onBarcode(""));
    }

    public static class EnglishLanguageMessageFormat {
        public String formatProductNotFoundMessage(String barcode) {
            return "Product not found: " + barcode;
        }

        public String formatPrice(String price) {
            return price;
        }

        public String formatEmptyBarcodeMessage() {
            return "Scanning error: empty barcode";
        }
    }

    public static class Catalog {
        private final Map<String, String> pricesByBarcode;

        public Catalog(Map<String, String> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        String findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }

    public static class Sale {
        private final EnglishLanguageMessageFormat englishLanguageMessageFormat;
        private final Catalog catalog;

        public Sale(EnglishLanguageMessageFormat englishLanguageMessageFormat, Catalog catalog) {
            this.englishLanguageMessageFormat = englishLanguageMessageFormat;
            this.catalog = catalog;
        }

        public String onBarcode(String barcode) {
            String result;
            if ("".equals(barcode)) {
                result = englishLanguageMessageFormat.formatEmptyBarcodeMessage();
            } else {
                final String maybePriceAsText = catalog.findPrice(barcode);
                if (maybePriceAsText == null) {
                    result = englishLanguageMessageFormat.formatProductNotFoundMessage(barcode);
                } else {
                    result = englishLanguageMessageFormat.formatPrice(maybePriceAsText);
                }
            }

            return result;
        }
    }
}
