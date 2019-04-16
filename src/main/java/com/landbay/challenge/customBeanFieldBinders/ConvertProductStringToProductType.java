package com.landbay.challenge.customBeanFieldBinders;

import com.landbay.challenge.enums.ProductType;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class ConvertProductStringToProductType extends AbstractBeanField<String> {
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException {
        try {
            return ProductType.valueOf(s);
        } catch (RuntimeException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}
