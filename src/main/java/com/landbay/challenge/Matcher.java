package com.landbay.challenge;

import com.landbay.challenge.filters.EqualProductTypeFilter;
import com.landbay.challenge.filters.FullFundedLoanFilter;
import com.landbay.challenge.filters.GreaterThanLoanTermFilter;
import com.landbay.challenge.sorters.OldestDateSorter;

import java.util.List;

public class Matcher {

    private EqualProductTypeFilter equalProductTypeFilter;
    private FullFundedLoanFilter fullFundedLoanFilter;
    private GreaterThanLoanTermFilter greaterThanLoanTermFilter;
    private OldestDateSorter oldestDateSorter;
    private Invester invester;

    public Matcher() {
        this.equalProductTypeFilter = new EqualProductTypeFilter();
        this.fullFundedLoanFilter = new FullFundedLoanFilter();
        this.greaterThanLoanTermFilter = new GreaterThanLoanTermFilter();
        this.oldestDateSorter = new OldestDateSorter();

        this.invester = new Invester();
    }

    public EqualProductTypeFilter getEqualProductTypeFilter() {
        return equalProductTypeFilter;
    }

    public FullFundedLoanFilter getFullFundedLoanFilter() {
        return fullFundedLoanFilter;
    }

    public GreaterThanLoanTermFilter getGreaterThanLoanTermFilter() {
        return greaterThanLoanTermFilter;
    }

    public Invester getInvester() {
        return invester;
    }

    public OldestDateSorter getOldestDateSorter() {
        return oldestDateSorter;
    }

    public void setEqualProductTypeFilter(EqualProductTypeFilter equalProductTypeFilter) {
        this.equalProductTypeFilter = equalProductTypeFilter;
    }

    public void setFullFundedLoanFilter(FullFundedLoanFilter fullFundedLoanFilter) {
        this.fullFundedLoanFilter = fullFundedLoanFilter;
    }

    public void setGreaterThanLoanTermFilter(GreaterThanLoanTermFilter greaterThanLoanTermFilter) {
        this.greaterThanLoanTermFilter = greaterThanLoanTermFilter;
    }

    public void setInvester(Invester invester) {
        this.invester = invester;
    }

    public void setOldestDateSorter(OldestDateSorter oldestDateSorter) {
        this.oldestDateSorter = oldestDateSorter;
    }

    public void match(List<Loan> loans, List<InvestmentRequest> investmentRequests) {
        loans.stream()
                .sorted(this.oldestDateSorter.order())
                .forEach(loan ->
                    investmentRequests.stream()
                            .filter(this.fullFundedLoanFilter.test(loan))
                            .filter(this.greaterThanLoanTermFilter.test(loan))
                            .filter(this.equalProductTypeFilter.test(loan))
                            .forEach(this.invester.processInvestmentRequest(loan))
                );
    }

}
