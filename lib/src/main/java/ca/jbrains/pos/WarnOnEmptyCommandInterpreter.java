package ca.jbrains.pos;

public class WarnOnEmptyCommandInterpreter {
    private final CommandInterpreter commandInterpreter;

    public WarnOnEmptyCommandInterpreter(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public String interpretCommandWarningOnEmptyCommands(TextCommand maybeTextCommand) {
        return maybeTextCommand == null
                ? "Empty command: please try again."
                : commandInterpreter.interpretCommand(maybeTextCommand);
    }
}