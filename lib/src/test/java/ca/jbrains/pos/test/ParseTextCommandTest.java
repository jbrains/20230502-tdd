package ca.jbrains.pos.test;

import ca.jbrains.pos.TextCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParseTextCommandTest {
    @Test
    void trimWhitespace() {
        Assertions.assertEquals(
                new TextCommand("12345"),
                TextCommand.parseTextCommand("\t  12345  \t   "));
    }

    @Test
    void empty() {
        Assertions.assertEquals(
                null,
                TextCommand.parseTextCommand(""));
    }

    @Test
    void emptyAfterTrimming() {
        Assertions.assertEquals(
                null,
                TextCommand.parseTextCommand("\t"));
    }
}
