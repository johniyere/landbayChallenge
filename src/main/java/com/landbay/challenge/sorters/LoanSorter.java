package com.landbay.challenge.sorters;

import com.landbay.challenge.Loan;

import java.util.Comparator;

public interface LoanSorter {
    Comparator<Loan> order();
}
