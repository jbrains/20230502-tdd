package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    void productFound() {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "CAD 7.95");
            put("23456", "CAD 12.50");
        }}));

        sale.onBarcode("12345");
        Assertions.assertEquals("CAD 7.95", display.getText());
    }

    @Test
    void anotherProductFound() {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(new HashMap<String, String>() {{
            put("12345", "CAD 7.95");
            put("23456", "CAD 12.50");
        }}));

        sale.onBarcode("23456");
        Assertions.assertEquals("CAD 12.50", display.getText());
    }

    @Test
    void productNotFound() {
        final Display display = new Display();
        final Sale sale = new Sale(display, new Catalog(Collections.emptyMap()));

        sale.onBarcode("99999");
        Assertions.assertEquals("Product not found: 99999", display.getText());
    }

    @Test
    void emptyBarcode() {
        final Display display = new Display();
        final Sale sale = new Sale(display, null);

        sale.onBarcode("");
        Assertions.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        public void displayProductNotFoundMessage(String barcode) {
            this.text = formatProductNotFoundMessage(barcode);
        }

        public String formatProductNotFoundMessage(String barcode) {
            return "Product not found: " + barcode;
        }

        public void displayPrice(String price) {
            this.text = formatPrice(price);
        }

        public String formatPrice(String price) {
            return price;
        }

        public void displayEmptyBarcodeMessage() {
            this.text = formatEmptyBarcodeMessage();
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
        private final Display display;
        private final Catalog catalog;

        public Sale(Display display, Catalog catalog) {
            this.display = display;
            this.catalog = catalog;
        }

        public String onBarcode(String barcode) {
            String result;
            if ("".equals(barcode)) {
                display.text = display.formatEmptyBarcodeMessage();
                result = display.formatEmptyBarcodeMessage();
            } else {
                final String maybePriceAsText = catalog.findPrice(barcode);
                if (maybePriceAsText == null) {
                    display.text = display.formatProductNotFoundMessage(barcode);
                    result = display.formatProductNotFoundMessage(barcode);
                } else {
                    display.text = display.formatPrice(maybePriceAsText);
                    result = display.formatPrice(maybePriceAsText);
                }
            }

            display.text = result;
            return result;
        }
    }
}
