package com.landbay.challenge;

import com.landbay.challenge.enums.Funded;
import com.landbay.challenge.products.TrackerProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class LoanTest {

    @Mock
    Investment investment;

    Loan loan;

    @BeforeEach
    void setUp() {
        this.loan = new Loan();
    }

    @Test
    void invest() {
        when(investment.getAmountInvested()).thenReturn(200);

        loan.setProduct(new TrackerProduct());
        loan.setTerm(10);
        loan.setCompletedDate(new GregorianCalendar(2015, 1, 1).getTime());
        loan.setLoanId(1);
        loan.setLoanAmount(1000);

        loan.invest(investment);

        assertThat(loan.getAmountInvested()).isEqualTo(200);
        assertThat(loan.getInvestments().size()).isEqualTo(1);
        assertThat(loan.getFunded()).isEqualByComparingTo(Funded.PARTIAL);
    }
}