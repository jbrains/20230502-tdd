package ca.jbrains.pos.test;

public class EnglishLanguageMessageFormat {
    public String formatProductNotFoundMessage(String barcode) {
        return "Product not found: " + barcode;
    }

    public String formatPrice(String price) {
        return price;
    }

    public String formatEmptyBarcodeMessage() {
        return "Scanning error: empty barcode";
    }
}
