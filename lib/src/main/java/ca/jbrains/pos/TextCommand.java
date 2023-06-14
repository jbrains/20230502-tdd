package ca.jbrains.pos;

import io.vavr.control.Either;

public record TextCommand(String text) {
    public static TextCommand parseTextCommand_Legacy(String commandAsText) {
        String sanitizedCommandAsText = commandAsText.trim();
        return sanitizedCommandAsText.isEmpty() ? null : new TextCommand(sanitizedCommandAsText);
    }

    public static Either<ParsingFailure, TextCommand> parseTextCommand(String commandAsText) {
        TextCommand legacy = parseTextCommand_Legacy(commandAsText);
        return legacy == null ? Either.left(new EmptyCommand()) : Either.right(legacy);
    }

    public record EmptyCommand() implements ParsingFailure {
    }
}