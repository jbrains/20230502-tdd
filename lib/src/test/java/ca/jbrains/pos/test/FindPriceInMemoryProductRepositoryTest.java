package ca.jbrains.pos.test;

import java.util.Map;

public class FindPriceInMemoryProductRepositoryTest extends FindPriceInProductRepositoryContract {
    protected ProductRepository productRepositoryWith(String barcode, int matchingPrice) {
        return new InMemoryProductRepository(Map.of(barcode, matchingPrice));
    }

    protected ProductRepository productRepositoryWithout(String missingBarcode) {
        return new InMemoryProductRepository(Map.of("::anything but " + missingBarcode + "::", 795));
    }

    private static class InMemoryProductRepository implements ProductRepository {
        private final Map<String, Integer> pricesByBarcode;

        public InMemoryProductRepository(Map<String, Integer> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Integer findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
