package ca.jbrains.pos.test;

import ca.jbrains.pos.Barcode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParseBarcodeTest {
    @Test
    void emptyBarcode() {
        Assertions.assertEquals(null, Barcode.parseBarcode(""));
    }
}
