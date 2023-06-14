package ca.jbrains.pos;

import io.vavr.Function1;
import io.vavr.control.Either;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.function.Function;
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
                                parsingFailure -> "Empty command: try again.",
                                commandInterpreter::interpretCommand)
                ))
                .forEach(out::println);
    }
}