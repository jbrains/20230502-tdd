package ca.jbrains.pos;

import ca.jbrains.pos.TextCommand.EmptyCommand;
import io.vavr.Function1;
import io.vavr.control.Either;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CommandProcessor {
    private final CommandInterpreter commandInterpreter;

    public CommandProcessor(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public void process(Reader textInput, Writer canvas) {
        final PrintWriter out = new PrintWriter(canvas, true);

        final Stream<String> textInputAsLines = new BufferedReader(textInput).lines();

        Function1<String, Either<ParsingFailure, TextCommand>> parseTextCommand = TextCommand::parseTextCommand;

        io.vavr.collection.Stream.ofAll(textInputAsLines)
                .map(parseTextCommand.andThen(
                        textCommandParsingResult -> textCommandParsingResult.fold(
                                CommandProcessor::handleTextCommandParsingFailureByWarningOnEmptyCommand,
                                commandInterpreter::interpretCommand)
                ))
                .forEach(out::println);
    }

    // REFACTOR Move to its own class and perhaps inject into this one.
    private static String handleTextCommandParsingFailureByWarningOnEmptyCommand(ParsingFailure parsingFailure) {
        // REFACTOR Replace with switch with pattern matching, when Java one days lets us.
        if (parsingFailure instanceof EmptyCommand) {
            return "Empty command: try again";
        } else {
            throw new UnsupportedOperationException("Somehow we have an object of type %s which has implemented %s"
                    .formatted(
                            parsingFailure.getClass().getName(),
                            Arrays.stream(parsingFailure.getClass().getInterfaces()).map(Class::getName).collect(Collectors.joining(", "))
                    )
            );
        }
    }
}