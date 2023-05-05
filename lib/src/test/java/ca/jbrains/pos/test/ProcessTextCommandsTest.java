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
    // - happy path, process lines until the stream ends.
    //    - 1 command
    //    - 0 commands
    //    - many commands

    @Test
    void oneCommand() {
        final StringReader simulatedTextInput = new StringReader("command 1\n");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas);

        Assertions.assertEquals("output from command 1\n", canvas.toString());
    }

    private void process(StringReader textInput, StringWriter canvas) {
        // This is complicated, but it's the easiest way I know to get lines of text
        // from a Reader. I'm open to better suggestions. :)
        final Stream<String> textInputAsLines = new BufferedReader(textInput).lines();

        // Our only test provides 1 line of text, so we can safely assume that there
        // is 1 line of text. If not, then this should fail with an exception.
        final String line = textInputAsLines.iterator().next();

        // We don't even need to look at the line of text yet!
        new PrintWriter(canvas, true).println("output from command 1");
    }
}