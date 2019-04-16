package com.landbay.challenge.customBeanFieldBinders;

import com.landbay.challenge.Investor;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class ConvestNameToInvestor extends AbstractBeanField<String> {
    @Override
    protected Object convert(String s) throws CsvDataTypeMismatchException {
        try {
            return new Investor(s);
        } catch (RuntimeException e) {
            throw new CsvDataTypeMismatchException(e.getMessage());
        }
    }
}
