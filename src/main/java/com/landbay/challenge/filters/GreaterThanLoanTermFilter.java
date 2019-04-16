package com.landbay.challenge.filters;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Loan;

import java.util.function.Predicate;

public class GreaterThanLoanTermFilter implements InvestmentRequestFilter {
    @Override
    public Predicate<InvestmentRequest> test(Loan loan) {
        return investmentRequest -> loan.getTerm() < investmentRequest.getTerm();
    }
}
