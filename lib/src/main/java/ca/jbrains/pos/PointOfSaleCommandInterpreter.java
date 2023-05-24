package ca.jbrains.pos;

public final class PointOfSaleCommandInterpreter implements CommandInterpreter {
    private final CommandInterpreter barcodeScannedCommandInterpreter;

    public PointOfSaleCommandInterpreter(CommandInterpreter barcodeScannedCommandInterpreter) {
        this.barcodeScannedCommandInterpreter = barcodeScannedCommandInterpreter;
    }

    private static String dispatchCommand(CommandInterpreter barcodeScannedCommandInterpreter, String trimmedCommand) {
        return trimmedCommand.isEmpty() ? "" : barcodeScannedCommandInterpreter.interpretCommand(trimmedCommand);
    }

    private static String sanitizeCommand(String textCommand) {
        return textCommand.trim();
    }

    public String interpretCommand(String textCommand) {
        return dispatchCommand(barcodeScannedCommandInterpreter, sanitizeCommand(textCommand));
    }
}