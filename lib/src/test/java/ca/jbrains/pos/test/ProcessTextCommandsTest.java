package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
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

        process(simulatedTextInput, canvas, Map.of("command 1", "output from command 1",
                "command 2", "output from command 2",
                "command 3", "output from command 3"));

        Assertions.assertEquals("output from command 1\n", canvas.toString());
    }

    @Test
    void noCommands() {
        final StringReader simulatedTextInput = new StringReader("");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas, Map.of("command 1", "output from command 1",
                "command 2", "output from command 2",
                "command 3", "output from command 3"));

        Assertions.assertEquals("", canvas.toString());
    }

    @Test
    void manyCommands() {
        final StringReader simulatedTextInput = new StringReader("command 1\ncommand 2\ncommand 3\n");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas, Map.of("command 1", "output from command 1",
                "command 2", "output from command 2",
                "command 3", "output from command 3"));

        Assertions.assertEquals("output from command 1\noutput from command 2\noutput from command 3\n", canvas.toString());
    }

    private void process(StringReader textInput, StringWriter canvas, Map<String, String> commandInterpreter) {
        // This is complicated, but it's the easiest way I know to get lines of text
        // from a Reader. I'm open to better suggestions. :)
        final Stream<String> textInputAsLines = new BufferedReader(textInput).lines();
        final PrintWriter out = new PrintWriter(canvas, true);

        final Iterator<String> textInputAsLinesIterator = textInputAsLines.iterator();
        while (textInputAsLinesIterator.hasNext()) {
            final String line = textInputAsLinesIterator.next();

            out.println(commandInterpreter.get(line));
        }
    }
}