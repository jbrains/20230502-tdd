package ca.jbrains.pos.test;

import ca.jbrains.pos.TextCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParseTextCommandTest {
    @Test
    void trimWhitespace() {
        Assertions.assertEquals(
                new TextCommand("12345"),
                TextCommand.parseTextCommand_Legacy("\t  12345  \t   "));
    }

    @Test
    void empty() {
        Assertions.assertEquals(
                null,
                TextCommand.parseTextCommand_Legacy(""));
    }

    @Test
    void emptyAfterTrimming() {
        Assertions.assertEquals(
                null,
                TextCommand.parseTextCommand_Legacy("\t"));
    }
}
