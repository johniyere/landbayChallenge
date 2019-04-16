package com.landbay.challenge.filters;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Loan;
import com.landbay.challenge.enums.Funded;
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
class FullFundedLoanFilterTest {
    @Mock
    Loan loan;

    @Mock
    InvestmentRequest investmentRequest;

    private FullFundedLoanFilter fullFundedLoanFilter;

    @BeforeEach
    void setUp() {
        this.fullFundedLoanFilter = new FullFundedLoanFilter();
    }

    @Nested
    class TestMethod {
        @Test
        void filterInvestmentRequestWhenLoanNotFunded() {
            when(loan.getFunded()).thenReturn(Funded.NOT);

            List<InvestmentRequest> actualInvestmentRequests = Stream.of(investmentRequest)
                    .filter(fullFundedLoanFilter.test(loan))
                    .collect(Collectors.toList());

            assertThat(actualInvestmentRequests).isNotEmpty();
        }

        @Test
        void notFilterInvestmentRequestWhenLoanFullyFunded() {
            when(loan.getFunded()).thenReturn(Funded.FULL);

            List<InvestmentRequest> actualInvestmentRequests = Stream.of(investmentRequest)
                    .filter(fullFundedLoanFilter.test(loan))
                    .collect(Collectors.toList());

            assertThat(actualInvestmentRequests).isEmpty();
        }

        @Test
        void filterInvestmentRequestWhenLoanPartiallyFunded() {
            when(loan.getFunded()).thenReturn(Funded.PARTIAL);

            List<InvestmentRequest> actualInvestmentRequests = Stream.of(investmentRequest)
                    .filter(fullFundedLoanFilter.test(loan))
                    .collect(Collectors.toList());

            assertThat(actualInvestmentRequests).isNotEmpty();
        }
    }


}