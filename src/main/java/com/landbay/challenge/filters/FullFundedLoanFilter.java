package com.landbay.challenge.filters;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Loan;
import com.landbay.challenge.enums.Funded;

import java.util.function.Predicate;

public class FullFundedLoanFilter implements InvestmentRequestFilter {
    @Override
    public Predicate<InvestmentRequest> test(Loan loan) {
        return investmentRequest -> loan.getFunded() != Funded.FULL;
    }
}

