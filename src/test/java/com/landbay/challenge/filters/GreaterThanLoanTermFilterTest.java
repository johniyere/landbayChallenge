package com.landbay.challenge.filters;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GreaterThanLoanTermFilterTest {

    @Mock
    Loan loan;

    @Mock
    InvestmentRequest investmentRequest;

    private GreaterThanLoanTermFilter greaterThanLoanTermFilter;

    @BeforeEach
    void setUp() {
        this.greaterThanLoanTermFilter = new GreaterThanLoanTermFilter();
    }

    @Test
    void test1() {
        when(this.loan.getTerm()).thenReturn(10);
        when(this.investmentRequest.getTerm()).thenReturn(12);

        List<InvestmentRequest> actualInvestmentRequests = Stream.of(this.investmentRequest)
                .filter(this.greaterThanLoanTermFilter.test(loan))
                .collect(Collectors.toList());

        assertThat(actualInvestmentRequests).isNotEmpty();
    }
}