package com.landbay.challenge;

import com.landbay.challenge.customBeanFieldBinders.ConvertLoanProduct;
import com.landbay.challenge.enums.Funded;
import com.landbay.challenge.products.Product;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Loan {

    @CsvBindByName
    private int loanId;

    @CsvBindByName
    private int loanAmount;

    @CsvBindByName
    private int term;

    @CsvBindByName
    @CsvDate("dd/MM/yyyy")
    private Date completedDate;


    @CsvCustomBindByName(converter = ConvertLoanProduct.class)
    private Product product;

    private int amountInvested = 0;

    private Funded funded = Funded.NOT;

    private List<Investment> investments = new ArrayList<>();

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCompletedDate() {
        return  completedDate;
    }

    public int getAmountInvested() {
        return amountInvested;
    }

    public Product getProduct() {
        return product;
    }

    public int getTerm() {
        return term;
    }

    public Funded getFunded() {
        return funded;
    }

    public List<Investment> getInvestments() {
        return investments;
    }

    public int getLoanAmountRemaining() {
        return loanAmount - amountInvested;
    }


    public void invest(Investment investment) {
        amountInvested += investment.getAmountInvested();
        investments.add(investment);
        checkAndUpdateFundedStatus();
    }

    private void checkAndUpdateFundedStatus() {
        funded = (this.getLoanAmountRemaining() == 0) ? Funded.FULL : Funded.PARTIAL;
    }

    @Override
    public String toString() {
        return "Loan (loanId=" + loanId
                + ", loanAmount=" + loanAmount
                + ", product=" + product
                + ", term=" + term
                + ", completedDate=" + completedDate
                + ", funded=" + funded
                + ", amountInvested=" + amountInvested
                + ", investments=" + investments
                + ")" ;

    }
}
