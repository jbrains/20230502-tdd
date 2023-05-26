package ca.jbrains.pos;

public record Barcode(String barcode) {
    public static Barcode parseBarcode(String maybeBarcodeAsText) {
        return "".equals(maybeBarcodeAsText)
                ? null
                : new Barcode(maybeBarcodeAsText);
    }

    public static Barcode parseBarcode(TextCommand textCommand) {
        final Barcode barcode = parseBarcode(textCommand.text());
        if (barcode == null) {
            throw new RuntimeException("Impossible parsing error: this line can never be empty.");
        } else {
            return barcode;
        }
    }
}
