package ca.jbrains.pos.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class FindPriceInProductRepositoryContract {
    @Test
    void productFound() {
        final ProductRepository productRepository = productRepositoryWith("12345", 795);
        Assertions.assertEquals(795, productRepository.findPrice("12345"));
    }

    protected abstract ProductRepository productRepositoryWith(String barcode, int matchingPrice);

    @Test
    void productNotFound() {
        final ProductRepository productRepository = productRepositoryWithout("12345");
        Assertions.assertEquals(null, productRepository.findPrice("12345"));
    }

    protected abstract ProductRepository productRepositoryWithout(String missingBarcode);
}
