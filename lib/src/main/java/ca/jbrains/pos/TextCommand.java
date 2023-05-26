package ca.jbrains.pos;

public record TextCommand(String text) {
    static TextCommand parseTextCommand(String commandAsText) {
        String sanitizedCommandAsText = commandAsText.trim();
        return sanitizedCommandAsText.isEmpty() ? null : new TextCommand(sanitizedCommandAsText);
    }
}