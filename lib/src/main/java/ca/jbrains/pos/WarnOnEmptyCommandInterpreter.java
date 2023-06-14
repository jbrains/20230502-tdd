package ca.jbrains.pos;

public class WarnOnEmptyCommandInterpreter implements CommandInterpreter {
    private final CommandInterpreter commandInterpreter;

    public WarnOnEmptyCommandInterpreter(CommandInterpreter commandInterpreter) {
        this.commandInterpreter = commandInterpreter;
    }

    public String interpretCommand(TextCommand maybeTextCommand) {
        return maybeTextCommand == null
                ? "Empty command: please try again."
                : commandInterpreter.interpretCommand(maybeTextCommand);
    }
}