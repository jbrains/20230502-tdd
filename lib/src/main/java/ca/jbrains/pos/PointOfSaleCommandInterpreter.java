package ca.jbrains.pos;

public final class PointOfSaleCommandInterpreter implements CommandInterpreter {
    private final CommandInterpreter barcodeScannedCommandInterpreter;

    public PointOfSaleCommandInterpreter(CommandInterpreter barcodeScannedCommandInterpreter) {
        this.barcodeScannedCommandInterpreter = barcodeScannedCommandInterpreter;
    }

    public String interpretCommand(TextCommand textCommand) {
        return this.barcodeScannedCommandInterpreter.interpretCommand(textCommand);
    }
}