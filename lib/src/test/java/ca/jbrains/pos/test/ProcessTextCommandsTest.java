package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ProcessTextCommandsTest {
    // Maybe we should split this behavior into two clear parts:
    // 1. Text UI loop
    // 2. Interpret Commands in memory

    @Test
    void oneCommand() {
        final StringReader simulatedTextInput = new StringReader("command 1\n");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas, Map.of("command 1", "output from command 1",
                "command 2", "output from command 2",
                "command 3", "output from command 3"));

        Assertions.assertEquals(List.of("output from command 1"), lines(canvas.toString()));
    }

    @Test
    void noCommands() {
        final StringReader simulatedTextInput = new StringReader("");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas, Map.of("command 1", "output from command 1",
                "command 2", "output from command 2",
                "command 3", "output from command 3"));

        Assertions.assertEquals(Collections.emptyList(), lines(canvas.toString()));
    }

    @Test
    void manyCommands() {
        final StringReader simulatedTextInput = new StringReader("command 1\ncommand 2\ncommand 3\n");
        final StringWriter canvas = new StringWriter();

        process(simulatedTextInput, canvas, Map.of("command 1", "output from command 1",
                "command 2", "output from command 2",
                "command 3", "output from command 3"));

        Assertions.assertEquals(
                List.of(
                        "output from command 1",
                        "output from command 2",
                        "output from command 3"),
                lines(canvas.toString()));
    }

    private List<String> lines(String multilineText) {
        return new BufferedReader(new StringReader(multilineText)).lines().toList();
    }

    private void process(StringReader textInput, StringWriter canvas, Map<String, String> commandInterpreter) {
        final PrintWriter out = new PrintWriter(canvas, true);

        final Stream<String> textInputAsLines = new BufferedReader(textInput).lines();

        final Stream<String> outputLines = interpretCommands(commandInterpreter, textInputAsLines);

        outputLines.forEachOrdered(outputLine -> out.println(outputLine));
    }

    private Stream<String> interpretCommands(Map<String, String> commandInterpreter, Stream<String> textInputAsLines) {
        return textInputAsLines.map(line -> commandInterpreter.get(line));
    }
}