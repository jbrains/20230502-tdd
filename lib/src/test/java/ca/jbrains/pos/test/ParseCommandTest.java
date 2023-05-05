package ca.jbrains.pos.test;

// CONTRACT:
// This behavior receives 1 line of text at a time,
// but it receives "raw text". This means that it
// will need to both clean up the text and then try
// to understand it.
//
// The client will have the responsibility of running
// a "UI loop", so this behavior expects never to receive
// a "quit" command.
public class ParseCommandTest {
    // Test list:
    // - happy path: interpret a valid command as "barcode scanned"
    // - extra whitespace: trim from the beginning and end
    // - empty: ignore the command (how?)
}
