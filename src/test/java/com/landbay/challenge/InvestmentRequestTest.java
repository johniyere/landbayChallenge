package com.landbay.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class InvestmentRequestTest {

    private InvestmentRequest investmentRequest;

    @BeforeEach
    void setUp() {
        this.investmentRequest = new InvestmentRequest();
    }

    @Test
    void decreaseInvestmentAmountBy10() {
        investmentRequest.setInvestmentAmount(100);
        investmentRequest.decreaseInvestmentAmount(10);

        assertThat(investmentRequest.getInvestmentAmount()).isEqualTo(90);
    }
}