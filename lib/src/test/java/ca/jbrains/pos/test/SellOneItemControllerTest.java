package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SellOneItemControllerTest {
    private int priceDisplayedInCents = -999999;
    private String barcodeOfProductNotFound = null;

    @Test
    void productFound() {
        final Display display = new Display() {
            @Override
            public void displayPrice(int priceInCents) {
                SellOneItemControllerTest.this.priceDisplayedInCents = priceInCents;
            }

            @Override
            public void displayProductNotFoundMessage(String missingBarcode) {
                throw new UnsupportedOperationException("Never call me!");
            }
        };

        final ProductRepository productRepository = ignoredBarcode -> Integer.valueOf(795);

        new SellOneItemController(productRepository, display).onBarcode("::barcode::");

        Assertions.assertEquals(795, priceDisplayedInCents);
    }

    @Test
    void productNotFound() {
        final Display display = new Display() {
            @Override
            public void displayPrice(int priceInCents) {
                throw new UnsupportedOperationException("Never call me!");
            }

            @Override
            public void displayProductNotFoundMessage(String missingBarcode) {
                SellOneItemControllerTest.this.barcodeOfProductNotFound = missingBarcode;
            }
        };

        final ProductRepository productRepository = ignoredBarcode -> null;

        new SellOneItemController(productRepository, display).onBarcode("::barcode::");

        Assertions.assertEquals("::barcode::", barcodeOfProductNotFound);
    }

    interface Display {
        void displayPrice(int priceInCents);

        void displayProductNotFoundMessage(String missingBarcode);
    }

    private class SellOneItemController {
        private final ProductRepository productRepository;
        private final Display display;

        public SellOneItemController(ProductRepository productRepository, Display display) {
            this.productRepository = productRepository;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            final Integer maybePrice = productRepository.findPrice(barcode);
            if (maybePrice == null)
                display.displayProductNotFoundMessage(barcode);
            else
                display.displayPrice(maybePrice);
        }
    }
}
