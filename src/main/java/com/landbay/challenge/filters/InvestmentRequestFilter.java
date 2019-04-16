package com.landbay.challenge.filters;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Loan;

import java.util.function.Predicate;

interface InvestmentRequestFilter {
    Predicate<InvestmentRequest> test(Loan loan);
}
