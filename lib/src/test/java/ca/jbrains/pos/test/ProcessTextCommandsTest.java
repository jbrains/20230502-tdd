package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
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

    @Test
    void noCommands() {
        final StringReader simulatedTextInput = new StringReader("");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas);

        Assertions.assertEquals("", canvas.toString());
    }

    private void process(StringReader textInput, StringWriter canvas) {
        // This is complicated, but it's the easiest way I know to get lines of text
        // from a Reader. I'm open to better suggestions. :)
        final Stream<String> textInputAsLines = new BufferedReader(textInput).lines();

        final Iterator<String> textInputAsLinesIterator = textInputAsLines.iterator();
        if (textInputAsLinesIterator.hasNext()) {
            // We don't even need to look at the line of text yet!
            new PrintWriter(canvas, true).println("output from command 1");
        }
    }
}