package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// CONTRACT:
// This behavior receives 1 line of text at a time,
// but it receives "raw text". This means that it
// will need to both clean up the text and then try
// to understand it.
public class InterpretCommandTest {
    // Test list:
    // - happy path: interpret a valid command as "barcode scanned"
    // - extra whitespace: trim from the beginning and end
    // - empty: ignore the command (how?)


    @Test
    void happyPath() {
        // This feels a lot like the tests for Sell One Item.
        // I don't want to repeat those ideas here, especially
        // when I _truly_ prefer to focus on the UI behavior.
        // How do I proceed?
        Assertions.assertEquals("CAD 7.95", interpretCommand("12345", new SellOneItemTest.Sale(null, null) {
            @Override
            public String onBarcode(String barcode) {
                return "CAD 7.95";
            }
        }));
    }

    private String interpretCommand(String textCommand, SellOneItemTest.Sale sale) {
        return sale.onBarcode(textCommand);
    }
}
