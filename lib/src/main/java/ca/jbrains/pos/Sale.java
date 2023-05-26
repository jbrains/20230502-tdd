package ca.jbrains.pos;

public class Sale implements CommandInterpreter {
    private final EnglishLanguageMessageFormat englishLanguageMessageFormat;
    private final Catalog catalog;

    public Sale(EnglishLanguageMessageFormat englishLanguageMessageFormat, Catalog catalog) {
        this.englishLanguageMessageFormat = englishLanguageMessageFormat;
        this.catalog = catalog;
    }

    public String onBarcode(String barcode) {
        // REFACTOR Replace with parseBarcode().
        if (failsParsing(barcode)) {
            // parsing failure
            return englishLanguageMessageFormat.formatEmptyBarcodeMessage();
        }

        // REFACTOR parsing success: Extract to onBarcode(Barcode).
        final String maybePriceAsText = catalog.findPrice(barcode);
        if (maybePriceAsText == null) {
            return englishLanguageMessageFormat.formatProductNotFoundMessage(barcode);
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
}
