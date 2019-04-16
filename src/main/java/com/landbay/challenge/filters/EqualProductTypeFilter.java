package com.landbay.challenge.filters;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Loan;

import java.util.function.Predicate;

public class EqualProductTypeFilter implements InvestmentRequestFilter {
    @Override
    public Predicate<InvestmentRequest> test(Loan loan) {
        return investmentRequest -> investmentRequest.getProductType().equals(loan.getProduct().getType());
    }
}
