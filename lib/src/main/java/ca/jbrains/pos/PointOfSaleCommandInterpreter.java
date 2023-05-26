package ca.jbrains.pos;

public final class PointOfSaleCommandInterpreter implements CommandInterpreter {
    private final CommandInterpreter barcodeScannedCommandInterpreter;

    public PointOfSaleCommandInterpreter(CommandInterpreter barcodeScannedCommandInterpreter) {
        this.barcodeScannedCommandInterpreter = barcodeScannedCommandInterpreter;
    }

    private String dispatchCommand(TextCommand textCommand) {
        return this.barcodeScannedCommandInterpreter.interpretCommandAsText(new TextCommand(textCommand.text()));
    }

    public String interpretCommandAsText(TextCommand textCommand) {
        return textCommand == null ? "" : dispatchCommand(textCommand);
    }
}