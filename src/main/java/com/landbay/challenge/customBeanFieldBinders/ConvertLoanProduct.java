package com.landbay.challenge.customBeanFieldBinders;

import com.landbay.challenge.enums.ProductType;
import com.landbay.challenge.products.FixedProduct;
import com.landbay.challenge.products.TrackerProduct;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class ConvertLoanProduct extends AbstractBeanField<String> {
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        try {
            ProductType productType = ProductType.valueOf(s);

            switch (productType) {
                case FIXED:
                    return new FixedProduct();
                case TRACKER:
                    return new TrackerProduct();
                default:
                    return null;
            }
        } catch (RuntimeException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}
