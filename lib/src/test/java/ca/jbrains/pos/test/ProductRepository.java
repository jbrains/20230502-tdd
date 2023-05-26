package ca.jbrains.pos.test;

interface ProductRepository {
    Integer findPrice(String barcode);
}
