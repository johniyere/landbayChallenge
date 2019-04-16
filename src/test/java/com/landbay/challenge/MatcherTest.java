package com.landbay.challenge;

import com.landbay.challenge.enums.Funded;
import com.landbay.challenge.filters.EqualProductTypeFilter;
import com.landbay.challenge.filters.FullFundedLoanFilter;
import com.landbay.challenge.filters.GreaterThanLoanTermFilter;
import com.landbay.challenge.sorters.OldestDateSorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

    Loan loan;

    @Mock
    InvestmentRequest investmentRequest1;

    @Mock
    InvestmentRequest investmentRequest2;

    private Matcher matcher;

    @BeforeEach
    void setUp() {
        this.matcher = new Matcher();
    }

    @Test
    void match() {

        when(oldestDateSorter.order()).thenCallRealMethod();
        when(fullFundedLoanFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
        when(equalProductTypeFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
        when(greaterThanLoanTermFilter.test(any(Loan.class))).thenReturn(investmentRequest -> true);
        when(invester.processInvestmentRequest(any(Loan.class))).thenCallRealMethod();

        loan = new Loan();
        loan.setLoanAmount(1000);
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);


        when(investmentRequest1.getInvestmentAmount()) .thenReturn(100);
        when(investmentRequest2.getInvestmentAmount()) .thenReturn(100);
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
}