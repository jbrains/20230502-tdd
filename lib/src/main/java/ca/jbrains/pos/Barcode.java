package ca.jbrains.pos;

public record Barcode(String barcode) {
    public static Barcode parseBarcode(String maybeBarcodeAsText) {
        return "".equals(maybeBarcodeAsText)
                ? null
                : new Barcode(maybeBarcodeAsText);
    }
}
