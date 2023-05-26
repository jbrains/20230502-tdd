package ca.jbrains.pos;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class CommandProcessor {
    private final CommandInterpreter commandInterpreter;

    public CommandProcessor(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public void process(Reader textInput, Writer canvas) {
        final PrintWriter out = new PrintWriter(canvas, true);

        final Stream<String> textInputAsLines = new BufferedReader(textInput).lines();

        final Stream<String> outputLines = textInputAsLines
                .map(TextCommand::parseTextCommand)
                .map(maybeTextCommand -> maybeTextCommand == null
                        ? "Empty command: please try again."
                        : commandInterpreter.interpretCommand(maybeTextCommand));

        outputLines.forEachOrdered(outputLine -> out.println(outputLine));
    }
}