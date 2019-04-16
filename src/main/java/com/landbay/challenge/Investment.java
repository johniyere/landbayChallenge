package com.landbay.challenge;

public class Investment {
    private Investor investor;
    private int amountInvested;

    public Investment(Investor investor, int amountInvested) {
        this.investor = investor;
        this.amountInvested = amountInvested;
    }

    @Override
    public String toString() {
        return "Investment [investor=" + investor
                + ", amountInvested=" + amountInvested
                + "]" ;
    }

    public int getAmountInvested() {
        return amountInvested;
    }

    public Investor getInvestor() {
        return investor;
    }
}
