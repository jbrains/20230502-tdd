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
    public String interpretCommand(TextCommand textCommand) {
        return onBarcode(Barcode.parseBarcode(textCommand));
    }
}
