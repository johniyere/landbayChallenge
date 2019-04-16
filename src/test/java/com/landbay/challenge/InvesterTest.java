package com.landbay.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvesterTest {

    @InjectMocks
    Loan loan;

    @Mock
    InvestmentRequest investmentRequest;

    @Mock
    Investment investment;

    Invester invester;
    @BeforeEach
    void setUp() {
        this.invester = new Invester();
    }

    @Test
    void processInvestmentRequest() {

        when(investmentRequest.getInvestmentAmount()).thenReturn(100);
        when(investment.getAmountInvested()).thenReturn(50);

        when(loan.getLoanAmountRemaining()).thenReturn(50);
        doNothing().when(investmentRequest).decreaseInvestmentAmount(anyInt());
        doNothing().when(loan).invest(any(Investment.class));


        Stream.of(investmentRequest)
                .forEach(this.invester.processInvestmentRequest(loan));

        verify(investmentRequest, times(1)).decreaseInvestmentAmount(anyInt());
        verify(loan, times(1)).invest(any(Investment.class));
    }
}