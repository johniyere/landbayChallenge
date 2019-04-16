package com.landbay.challenge.products;

import com.landbay.challenge.enums.ProductType;

public class Product {
    ProductType type;

    public Product(ProductType type) {
        this.type = type;
    }

    public ProductType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Product [type="+ type + "]";
    }
}
