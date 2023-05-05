package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.stream.Stream;

public class ProcessTextCommandsTest {
    // Test List:
    // - happy path, process lines until "quit":
    //    - 1 command, then quit
    //    - many commands, then quit
    //    - premature end of stream

    @Test
    void quitBeforeAnyCommands() {
        final StringReader simulatedTextInput = new StringReader("quit\n");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas);

        Assertions.assertEquals("", canvas.toString());
    }

    @Test
    void oneCommandThenQuit() {
        final StringReader simulatedTextInput = new StringReader("12345\nquit\n");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas);

        Assertions.assertEquals("CAD 7.95\n", canvas.toString());
    }

    private void process(StringReader textInput, StringWriter canvas) {
        final Stream<String> textInputAsLines = new BufferedReader(textInput).lines();
        final PrintWriter out = new PrintWriter(canvas, true);

        for (String line : textInputAsLines.toList()) {
            if ("quit".equals(line)) break;
            out.println("CAD 7.95");
        }
    }
}