package ca.jbrains.pos.test;

public class Sale implements CommandInterpreter {
    private final EnglishLanguageMessageFormat englishLanguageMessageFormat;
    private final Catalog catalog;

    public Sale(EnglishLanguageMessageFormat englishLanguageMessageFormat, Catalog catalog) {
        this.englishLanguageMessageFormat = englishLanguageMessageFormat;
        this.catalog = catalog;
    }

    public String onBarcode(String barcode) {
        if ("".equals(barcode)) {
            return englishLanguageMessageFormat.formatEmptyBarcodeMessage();
        }

        final String maybePriceAsText = catalog.findPrice(barcode);
        if (maybePriceAsText == null) {
            return englishLanguageMessageFormat.formatProductNotFoundMessage(barcode);
        } else {
            return englishLanguageMessageFormat.formatPrice(maybePriceAsText);
        }
    }

    @Override
    public String interpretCommand(String line) {
        return onBarcode(line);
    }
}
