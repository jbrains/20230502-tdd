package ca.jbrains.pos;

public class Sale implements CommandInterpreter {
    private final EnglishLanguageMessageFormat englishLanguageMessageFormat;
    private final Catalog catalog;

    public Sale(EnglishLanguageMessageFormat englishLanguageMessageFormat, Catalog catalog) {
        this.englishLanguageMessageFormat = englishLanguageMessageFormat;
        this.catalog = catalog;
    }

    public String parseCommandAsBarcodeThenHandleBarcode(String maybeBarcodeAsText) {
        final Barcode barcode = parseBarcode(maybeBarcodeAsText);
        if (barcode == null) {
            return englishLanguageMessageFormat.formatEmptyBarcodeMessage();
        }

        return reallyOnBarcode(barcode);
    }

    private static Barcode parseBarcode(String maybeBarcodeAsText) {
        return "".equals(maybeBarcodeAsText)
                ? null
                : new Barcode(maybeBarcodeAsText);
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

    @Override
    public String interpretCommand(String line) {
        return parseCommandAsBarcodeThenHandleBarcode(line);
    }

    private record Barcode(String barcode) {
    }
}
