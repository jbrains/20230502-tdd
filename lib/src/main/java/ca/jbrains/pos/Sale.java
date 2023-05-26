package ca.jbrains.pos;

public class Sale implements CommandInterpreter {
    private final EnglishLanguageMessageFormat englishLanguageMessageFormat;
    private final Catalog catalog;

    public Sale(EnglishLanguageMessageFormat englishLanguageMessageFormat, Catalog catalog) {
        this.englishLanguageMessageFormat = englishLanguageMessageFormat;
        this.catalog = catalog;
    }

    public String onBarcode(String maybeBarcodeAsText) {
        // REFACTOR Replace with parseBarcode().
        if (failsParsing(maybeBarcodeAsText)) {
            // parsing failure
            return englishLanguageMessageFormat.formatEmptyBarcodeMessage();
        } else {
            final Barcode barcode = new Barcode(maybeBarcodeAsText);
            return reallyOnBarcode(barcode);
        }
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
