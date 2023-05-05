package ca.jbrains.pos.test;

public class ParseThenInterpretCommandsFromSimulatedStdinTest {
    // Test List:
    // - happy path, only 1 line of text
    // - happy path, many lines of text
    // - no lines of text (what should happen?)
    //
    // WAIT!! Now I have a group of tests which are related
    // to only 1 line of text at a time. This seems separate
    // from processing an entire _stream_ of text (many lines).
    // I guess these are tests for a smaller object!
    // - 1 line of text, empty
    // - 1 line of text, quit
    // - 1 line of text, extra whitespace
}
