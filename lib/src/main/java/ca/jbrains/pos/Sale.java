package ca.jbrains.pos;

public class Sale implements CommandInterpreter {
    private final EnglishLanguageMessageFormat englishLanguageMessageFormat;
    private final Catalog catalog;

    public Sale(EnglishLanguageMessageFormat englishLanguageMessageFormat, Catalog catalog) {
        this.englishLanguageMessageFormat = englishLanguageMessageFormat;
        this.catalog = catalog;
    }

    public String onBarcode(Barcode barcode) {
        final String maybePriceAsText = catalog.findPrice(barcode.barcode());
        if (maybePriceAsText == null) {
            return englishLanguageMessageFormat.formatProductNotFoundMessage(barcode.barcode());
        } else {
            return englishLanguageMessageFormat.formatPrice(maybePriceAsText);
        }
    }

    @Override
    public String interpretCommand(String line) {
        final Barcode barcode = Barcode.parseBarcode(line);
        if (barcode == null) {
            // We should apply this same concept to the Command in the layer above!
            throw new RuntimeException("Impossible parsing error: this line can never be empty.");
        } else {
            return onBarcode(barcode);
        }
    }
}
