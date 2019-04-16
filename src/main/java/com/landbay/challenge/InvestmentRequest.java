package com.landbay.challenge;

import com.landbay.challenge.customBeanFieldBinders.ConvertNameToInvestor;
import com.landbay.challenge.customBeanFieldBinders.ConvertProductStringToProductType;
import com.landbay.challenge.enums.ProductType;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

public class InvestmentRequest {

    @CsvCustomBindByName(converter = ConvertNameToInvestor.class)
    private Investor investor;

    @CsvBindByName
    private int investmentAmount;

    @CsvCustomBindByName(converter = ConvertProductStringToProductType.class)
    private ProductType productType;

    @CsvBindByName
    private int term;


    public Investor getInvestor() {
        return investor;
    }

    public int getInvestmentAmount() {
        return investmentAmount;
    }

    public ProductType getProductType() {
        return productType;
    }

    public int getTerm() {
        return term;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public void setInvestmentAmount(int investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void decreaseInvestmentAmount(int amount) {
        investmentAmount -= amount;
    }

    @Override
    public String toString() {
        return "InvestmentRequest (investor=" + investor
                + ", investmentAmount=" + investmentAmount
                + ", productType=" + productType
                + ", term=" + term
                + ")";
    }
}
