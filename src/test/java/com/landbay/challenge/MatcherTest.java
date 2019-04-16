package com.landbay.challenge;

import com.landbay.challenge.enums.Funded;
import com.landbay.challenge.filters.EqualProductTypeFilter;
import com.landbay.challenge.filters.FullFundedLoanFilter;
import com.landbay.challenge.filters.GreaterThanLoanTermFilter;
import com.landbay.challenge.sorters.OldestDateSorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatcherTest {

    @Mock
    EqualProductTypeFilter equalProductTypeFilter;

    @Mock
    private FullFundedLoanFilter fullFundedLoanFilter;

    @Mock
    private GreaterThanLoanTermFilter greaterThanLoanTermFilter;

    @Mock
    private OldestDateSorter oldestDateSorter;

    @Mock
    private Invester invester;

    private Loan loan;

    @Mock
    InvestmentRequest investmentRequest1;

    @Mock
    InvestmentRequest investmentRequest2;

    private Matcher matcher;

    @BeforeEach
    void setUp() {
        this.matcher = new Matcher();
    }


    @Nested
    class MatchMethod {

        @Test
        void loanShouldHave2InvestmentsAndShouldBePartiallyFunded() {

            when(oldestDateSorter.order()).thenCallRealMethod();
            when(fullFundedLoanFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(equalProductTypeFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(greaterThanLoanTermFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(invester.processInvestmentRequest(any(Loan.class))).thenCallRealMethod();

            loan = new Loan();
            loan.setLoanAmount(1000);
            List<Loan> loans = new ArrayList<>();
            loans.add(loan);


            when(investmentRequest1.getInvestmentAmount()).thenReturn(100);
            when(investmentRequest2.getInvestmentAmount()).thenReturn(100);
            List<InvestmentRequest> investmentRequests = new ArrayList<>();

            investmentRequests.add(investmentRequest1);
            investmentRequests.add(investmentRequest2);


            matcher.setOldestDateSorter(oldestDateSorter);
            matcher.setEqualProductTypeFilter(equalProductTypeFilter);
            matcher.setFullFundedLoanFilter(fullFundedLoanFilter);
            matcher.setInvester(invester);
            matcher.setGreaterThanLoanTermFilter(greaterThanLoanTermFilter);

            matcher.match(loans, investmentRequests);


            verify(oldestDateSorter, times(1)).order();
            assertEquals(loan.getInvestments().size(), 2);
            assertEquals(loan.getFunded(), Funded.PARTIAL);
        }

        @Test
        void loanShouldHave1InvestmentsAndShouldBePartiallyFunded() {

            when(oldestDateSorter.order()).thenCallRealMethod();
            when(fullFundedLoanFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(equalProductTypeFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(greaterThanLoanTermFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(invester.processInvestmentRequest(any(Loan.class))).thenCallRealMethod();

            loan = new Loan();
            loan.setLoanAmount(1000);
            List<Loan> loans = new ArrayList<>();
            loans.add(loan);


            when(investmentRequest1.getInvestmentAmount()).thenReturn(1000);
            List<InvestmentRequest> investmentRequests = new ArrayList<>();

            investmentRequests.add(investmentRequest1);


            matcher.setOldestDateSorter(oldestDateSorter);
            matcher.setEqualProductTypeFilter(equalProductTypeFilter);
            matcher.setFullFundedLoanFilter(fullFundedLoanFilter);
            matcher.setInvester(invester);
            matcher.setGreaterThanLoanTermFilter(greaterThanLoanTermFilter);

            matcher.match(loans, investmentRequests);


            verify(oldestDateSorter, times(1)).order();
            verify(equalProductTypeFilter, times(1)).test(any(Loan.class));
            verify(fullFundedLoanFilter, times(1)).test(any(Loan.class));
            verify(greaterThanLoanTermFilter, times(1)).test(any(Loan.class));
            verify(invester, times(1)).processInvestmentRequest(any(Loan.class));

            assertEquals(loan.getInvestments().size(), 1);
            assertEquals(loan.getFunded(), Funded.FULL);
        }

        @Test
        void loanShouldHave2InvestmentsAndShouldBeFullyFunded() {
            when(oldestDateSorter.order()).thenCallRealMethod();
            when(fullFundedLoanFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(equalProductTypeFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(greaterThanLoanTermFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(invester.processInvestmentRequest(any(Loan.class))).thenCallRealMethod();

            loan = new Loan();
            loan.setLoanAmount(1000);
            List<Loan> loans = new ArrayList<>();
            loans.add(loan);


            when(investmentRequest1.getInvestmentAmount()).thenReturn(500);
            when(investmentRequest2.getInvestmentAmount()).thenReturn(500);
            List<InvestmentRequest> investmentRequests = new ArrayList<>();

            investmentRequests.add(investmentRequest1);
            investmentRequests.add(investmentRequest2);


            matcher.setOldestDateSorter(oldestDateSorter);
            matcher.setEqualProductTypeFilter(equalProductTypeFilter);
            matcher.setFullFundedLoanFilter(fullFundedLoanFilter);
            matcher.setInvester(invester);
            matcher.setGreaterThanLoanTermFilter(greaterThanLoanTermFilter);

            matcher.match(loans, investmentRequests);


            verify(oldestDateSorter, times(1)).order();
            assertEquals(loan.getInvestments().size(), 2);
            assertEquals(loan.getFunded(), Funded.FULL);
        }

        @Test
        void loanShouldNotBeFundedAsProductTypeNotEqual() {
            when(oldestDateSorter.order()).thenCallRealMethod();
            when(fullFundedLoanFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
            when(equalProductTypeFilter.test(any(Loan.class))).thenReturn(investmentRequest -> false);

            loan = new Loan();
            loan.setLoanAmount(1000);
            List<Loan> loans = new ArrayList<>();
            loans.add(loan);

            List<InvestmentRequest> investmentRequests = new ArrayList<>();


            matcher.setOldestDateSorter(oldestDateSorter);
            matcher.setEqualProductTypeFilter(equalProductTypeFilter);
            matcher.setFullFundedLoanFilter(fullFundedLoanFilter);


            matcher.match(loans, investmentRequests);
            investmentRequests.add(investmentRequest1);
            investmentRequests.add(investmentRequest2);

            verify(fullFundedLoanFilter, times(1)).test(any(Loan.class));
            verify(equalProductTypeFilter, times(1)).test(any(Loan.class));
            verify(greaterThanLoanTermFilter, never()).test(any(Loan.class));
            verify(invester, never()).processInvestmentRequest(any(Loan.class));

            assertEquals(loan.getInvestments().size(), 0);
            assertEquals(loan.getFunded(), Funded.NOT);
        }


    }
}