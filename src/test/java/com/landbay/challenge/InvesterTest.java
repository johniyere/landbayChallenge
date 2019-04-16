package com.landbay.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvesterTest {

    @Mock
    Loan loan;

    @Mock
    InvestmentRequest investmentRequest;

    private Invester invester;

    @BeforeEach
    void setUp() {
        this.invester = new Invester();
    }

    @Nested
    class ProcessInvestmentRequestMethod {
        @Test
        void callInvestMethodsWhenLoanAmountGreaterThanInvestmentRequestAmounts() {

            when(investmentRequest.getInvestmentAmount()).thenReturn(100);
            when(loan.getLoanAmountRemaining()).thenReturn(50);

            doNothing().when(investmentRequest).decreaseInvestmentAmount(anyInt());
            doNothing().when(loan).invest(any(Investment.class));


            Stream.of(investmentRequest)
                    .forEach(invester.processInvestmentRequest(loan));

            verify(investmentRequest, times(1)).decreaseInvestmentAmount(anyInt());
            verify(loan, times(1)).invest(any(Investment.class));
        }

        @Test
        void callInvestMethodsWhenInvestmentRequestAmountGreaterThanLoanAmount() {

            when(investmentRequest.getInvestmentAmount()).thenReturn(50);
            when(loan.getLoanAmountRemaining()).thenReturn(100);

            doNothing().when(investmentRequest).decreaseInvestmentAmount(anyInt());
            doNothing().when(loan).invest(any(Investment.class));


            Stream.of(investmentRequest)
                    .forEach(invester.processInvestmentRequest(loan));

            verify(investmentRequest, times(1)).decreaseInvestmentAmount(anyInt());
            verify(loan, times(1)).invest(any(Investment.class));
        }

        @Test
        void doesNotCallInvestMethodWhenAmountsAreNot0() {
            when(investmentRequest.getInvestmentAmount()).thenReturn(0);
            when(loan.getLoanAmountRemaining()).thenReturn(0);

            Stream.of(investmentRequest)
                    .forEach(invester.processInvestmentRequest(loan));

            verify(investmentRequest, never()).decreaseInvestmentAmount(anyInt());
            verify(loan, never()).invest(any(Investment.class));
        }

        @Test
        void doesNotCallInvestMethodWhenInvestmentRequestAmountsIs0() {
            when(investmentRequest.getInvestmentAmount()).thenReturn(0);
            when(loan.getLoanAmountRemaining()).thenReturn(50);

            Stream.of(investmentRequest)
                    .forEach(invester.processInvestmentRequest(loan));

            verify(investmentRequest, never()).decreaseInvestmentAmount(anyInt());
            verify(loan, never()).invest(any(Investment.class));
        }

        @Test
        void doesNotCallInvestMethodWhenLoanAmountIs0() {
            when(investmentRequest.getInvestmentAmount()).thenReturn(50);
            when(loan.getLoanAmountRemaining()).thenReturn(0);

            Stream.of(investmentRequest)
                    .forEach(invester.processInvestmentRequest(loan));

            verify(investmentRequest, never()).decreaseInvestmentAmount(anyInt());
            verify(loan, never()).invest(any(Investment.class));
        }
    }


}