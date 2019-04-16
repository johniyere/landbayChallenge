package com.landbay.challenge.sorters;

import com.landbay.challenge.Loan;

import java.util.Comparator;

public class OldestDateSorter implements LoanSorter {
    @Override
    public Comparator<Loan> order() {
        return Comparator.comparing(Loan::getCompletedDate);
    }
}
