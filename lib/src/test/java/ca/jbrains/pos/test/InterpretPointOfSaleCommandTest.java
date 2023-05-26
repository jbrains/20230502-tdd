package ca.jbrains.pos.test;

import ca.jbrains.pos.CommandInterpreter;
import ca.jbrains.pos.PointOfSaleCommandInterpreter;
import ca.jbrains.pos.TextCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InterpretPointOfSaleCommandTest {
    @Test
    void happyPath() {
        // Metaconstant trick in order to signal more clearly that
        // "We interpreted this as a barcode."
        final CommandInterpreter barcodeScannedInterpreter = (textCommand) -> "::barcode scanned::";
        Assertions.assertEquals("::barcode scanned::", new PointOfSaleCommandInterpreter(barcodeScannedInterpreter).interpretCommandAsText(new TextCommand("12345")));
    }
}
