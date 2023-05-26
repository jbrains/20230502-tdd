package ca.jbrains.pos;

public interface CommandInterpreter {
    String interpretCommandAsText(TextCommand textCommand);
}
