package ca.jbrains.pos;

public final class PointOfSaleCommandInterpreter implements CommandInterpreter {
    private final CommandInterpreter barcodeScannedCommandInterpreter;

    public PointOfSaleCommandInterpreter(CommandInterpreter barcodeScannedCommandInterpreter) {
        this.barcodeScannedCommandInterpreter = barcodeScannedCommandInterpreter;
    }

    public String interpretCommand(TextCommand textCommand) {
        // Every command happens to be a Barcode so far!
        return this.barcodeScannedCommandInterpreter.interpretCommand(textCommand);
    }
}