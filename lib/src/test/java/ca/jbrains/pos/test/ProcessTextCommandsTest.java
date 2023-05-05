package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;

public class ProcessTextCommandsTest {
    // Test List:
    // - happy path, process lines until "quit":
    //    - quit on the first line
    //    - 1 command, then quit
    //    - many commands, then quit

    @Test
    void quitBeforeAnyCommands() {
        final StringReader simulatedTextInput = new StringReader("quit\n");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas);

        Assertions.assertEquals("", canvas.toString());
    }

    private void process(StringReader textInput, StringWriter canvas) {
    }
}