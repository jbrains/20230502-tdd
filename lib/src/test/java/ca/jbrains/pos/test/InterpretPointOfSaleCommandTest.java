package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// CONTRACT:
// This behavior receives 1 line of text at a time,
// but it receives "raw text". This means that it
// will need to both clean up the text and then try
// to understand it.
public class InterpretPointOfSaleCommandTest {
    // Test list:
    // - happy path: interpret a valid command as "barcode scanned"
    // - extra whitespace: trim from the beginning and end
    // - empty: ignore the command (how?)


    @Test
    void happyPath() {
        // Metaconstant trick in order to signal more clearly that
        // "We interpreted this as a barcode."
        Assertions.assertEquals("::barcode scanned::", interpretCommand("12345", line -> "::barcode scanned::"));
    }

    private String interpretCommand(String textCommand, CommandInterpreter barcodeScannedCommandInterpreter) {
        return barcodeScannedCommandInterpreter.interpretCommand(textCommand);
    }
}
