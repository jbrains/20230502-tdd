package ca.jbrains.pos;

public class Sale implements CommandInterpreter {
    private final EnglishLanguageMessageFormat englishLanguageMessageFormat;
    private final Catalog catalog;

    public Sale(EnglishLanguageMessageFormat englishLanguageMessageFormat, Catalog catalog) {
        this.englishLanguageMessageFormat = englishLanguageMessageFormat;
        this.catalog = catalog;
    }

    public String onBarcode(String maybeBarcodeAsText) {
        String responseMessage;
        final Barcode barcode;

        // REFACTOR Replace with parseBarcode().
        if (failsParsing(maybeBarcodeAsText)) {
            // parsing failure
            barcode = null;
            responseMessage = englishLanguageMessageFormat.formatEmptyBarcodeMessage();
        } else {
            barcode = new Barcode(maybeBarcodeAsText);
            responseMessage = reallyOnBarcode(barcode);
        }
        return responseMessage;
    }

    // REFACTOR parsing success: Extract to onBarcode(Barcode).
    private String reallyOnBarcode(Barcode barcode) {
        final String maybePriceAsText = catalog.findPrice(barcode.barcode());
        if (maybePriceAsText == null) {
            return englishLanguageMessageFormat.formatProductNotFoundMessage(barcode.barcode());
        } else {
            return englishLanguageMessageFormat.formatPrice(maybePriceAsText);
        }
    }

    private static boolean failsParsing(String barcode) {
        return "".equals(barcode);
    }

    @Override
    public String interpretCommand(String line) {
        return onBarcode(line);
    }

    private record Barcode(String barcode) {
    }
}
