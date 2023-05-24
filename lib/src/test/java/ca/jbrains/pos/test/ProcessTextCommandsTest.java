package ca.jbrains.pos.test;

import ca.jbrains.pos.CommandInterpreter;
import ca.jbrains.pos.CommandProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ProcessTextCommandsTest {
    // These tests no longer worry about _how_ to interpret commands,
    // but instead only about how to use a Command Interpreter.
    // Now we can focus on interpreting text commands in a way that
    // matches the interface of LightweightCommandInterpreter.

    @Test
    void oneCommand() {
        final StringReader simulatedTextInput = new StringReader("command 1\n");
        final StringWriter canvas = new StringWriter();

        new CommandProcessor(new LightweightCommandInterpreter(
                Map.of("command 1", "output from command 1",
                        "command 2", "output from command 2",
                        "command 3", "output from command 3"))).process(simulatedTextInput, canvas);

        Assertions.assertEquals(List.of("output from command 1"), lines(canvas.toString()));
    }

    @Test
    void noCommands() {
        final StringReader simulatedTextInput = new StringReader("");
        final StringWriter canvas = new StringWriter();

        new CommandProcessor(new LightweightCommandInterpreter(
                Map.of("command 1", "output from command 1",
                        "command 2", "output from command 2",
                        "command 3", "output from command 3"))).process(simulatedTextInput, canvas);

        Assertions.assertEquals(Collections.emptyList(), lines(canvas.toString()));
    }

    @Test
    void manyCommands() {
        final StringReader simulatedTextInput = new StringReader("command 1\ncommand 2\ncommand 3\n");
        final StringWriter canvas = new StringWriter();

        new CommandProcessor(new LightweightCommandInterpreter(
                Map.of("command 1", "output from command 1",
                        "command 2", "output from command 2",
                        "command 3", "output from command 3"))).process(simulatedTextInput, canvas);

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

    private static final class LightweightCommandInterpreter implements CommandInterpreter {
        private final Map<String, String> script;

        private LightweightCommandInterpreter(Map<String, String> script) {
            this.script = script;
        }

        @Override
        public String interpretCommand(String line) {
            return script.get(line);
        }
    }
}