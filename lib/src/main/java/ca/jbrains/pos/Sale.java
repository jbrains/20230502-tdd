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

        String responseMessage;
        if (barcode == null) {
            responseMessage = englishLanguageMessageFormat.formatEmptyBarcodeMessage();
        } else {
            responseMessage = reallyOnBarcode(barcode);
        }
        return responseMessage;
    }

    private static Barcode parseBarcode(String maybeBarcodeAsText) {
        final Barcode barcode;

        if (failsParsing(maybeBarcodeAsText)) {
            barcode = null;
        } else {
            barcode = new Barcode(maybeBarcodeAsText);
        }

        return barcode;
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
        return parseCommandAsBarcodeThenHandleBarcode(line);
    }

    private record Barcode(String barcode) {
    }
}
