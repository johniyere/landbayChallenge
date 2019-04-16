package com.landbay.challenge;

import java.util.function.Consumer;

public class Invester {
    public Consumer<InvestmentRequest> processInvestmentRequest(Loan loan) {
        return investmentRequest -> {
            int investmentAmount = Math.min(investmentRequest.getInvestmentAmount(), loan.getLoanAmountRemaining());

            if (investmentAmount > 0) {
                this.invest(loan, investmentRequest, investmentAmount);
            }
        };
    }

    private void invest(Loan loan, InvestmentRequest investmentRequest, int investmentAmount) {
        investmentRequest.decreaseInvestmentAmount(investmentAmount);
        loan.invest(new Investment(investmentRequest.getInvestor(), investmentAmount));
    }
}
