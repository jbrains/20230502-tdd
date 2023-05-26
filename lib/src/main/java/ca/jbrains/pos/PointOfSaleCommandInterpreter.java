package ca.jbrains.pos;

public final class PointOfSaleCommandInterpreter implements CommandInterpreter {
    private final CommandInterpreter barcodeScannedCommandInterpreter;

    public PointOfSaleCommandInterpreter(CommandInterpreter barcodeScannedCommandInterpreter) {
        this.barcodeScannedCommandInterpreter = barcodeScannedCommandInterpreter;
    }

    private String dispatchCommand(TextCommand textCommand) {
        return this.barcodeScannedCommandInterpreter.interpretCommand(textCommand.text());
    }

    public String interpretCommand(String commandAsText) {
        final TextCommand textCommand = TextCommand.parseTextCommand(commandAsText);
        return textCommand == null ? "" : dispatchCommand(textCommand);
    }
}