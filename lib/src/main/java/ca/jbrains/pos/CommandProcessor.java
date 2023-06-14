package ca.jbrains.pos;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.stream.Stream;

public final class CommandProcessor {
    private final CommandInterpreter commandInterpreter;

    public CommandProcessor(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public void process(Reader textInput, Writer canvas) {
        final PrintWriter out = new PrintWriter(canvas, true);

        final Stream<String> textInputAsLines = new BufferedReader(textInput).lines();

        // REFACTOR Perhaps this is nicer with Either: map(parseTextCommand).bimap(handleInvalidCommand, interpretCommand)
        final Stream<String> outputLines = textInputAsLines
                .map(TextCommand::parseTextCommand)
                .map(commandInterpreter::interpretCommand);

        outputLines.forEachOrdered(out::println);
    }
}