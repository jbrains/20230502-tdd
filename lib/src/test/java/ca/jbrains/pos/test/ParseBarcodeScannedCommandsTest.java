package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;

public class ParseBarcodeScannedCommandsTest {
    // Test List:
    // - happy path
    // - empty barcode
    // - quit (end program)

    @Test
    void happyPath() {
        final StringReader simulatedInput = new StringReader("12345\n");
        // Hmmm... I guess my brain wants to think about multiple lines of input
        // at the same time that it wants to parse a single command. Maybe my code
        // is doing more than parsing commands. :)
        Assertions.assertEquals(new BarcodeScannedCommand("12345"), parseCommands(simulatedInput));
    }
}
