package com.landbay.challenge.filters;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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

    @Nested
    class TestMethod {
        @Test
        void notFilterInvestmentRequestWithGreaterTerm() {
            when(loan.getTerm()).thenReturn(10);
            when(investmentRequest.getTerm()).thenReturn(12);

            List<InvestmentRequest> actualInvestmentRequests = Stream.of(investmentRequest)
                    .filter(greaterThanLoanTermFilter.test(loan))
                    .collect(Collectors.toList());

            assertThat(actualInvestmentRequests).isNotEmpty();
        }

        @Test
        void filterInvestmentRequestWithLesserTerm() {
            when(loan.getTerm()).thenReturn(10);
            when(investmentRequest.getTerm()).thenReturn(9);

            List<InvestmentRequest> actualInvestmentRequests = Stream.of(investmentRequest)
                    .filter(greaterThanLoanTermFilter.test(loan))
                    .collect(Collectors.toList());

            assertThat(actualInvestmentRequests).isEmpty();
        }

        @Test
        void filterInvestmentRequestWithEqualTerm() {
            when(loan.getTerm()).thenReturn(10);
            when(investmentRequest.getTerm()).thenReturn(10);

            List<InvestmentRequest> actualInvestmentRequests = Stream.of(investmentRequest)
                    .filter(greaterThanLoanTermFilter.test(loan))
                    .collect(Collectors.toList());

            assertThat(actualInvestmentRequests).isEmpty();
        }
    }


}