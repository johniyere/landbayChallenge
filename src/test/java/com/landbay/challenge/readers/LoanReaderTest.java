package com.landbay.challenge.readers;

import com.landbay.challenge.Loan;
import com.landbay.challenge.products.FixedProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LoanReaderTest {

    private LoanReader loanReader;
    @BeforeEach
    void setUp() {
    }

    @Test
    void readCSVToList() throws FileNotFoundException {
        this.loanReader = new LoanReader("src/test/resources/loans-test-data.csv");

        Date completedDate = new GregorianCalendar(2015, 0, 03).getTime();

        Loan expectedLoan = new Loan();
        expectedLoan.setLoanId(3);
        expectedLoan.setLoanAmount(100000);
        expectedLoan.setCompletedDate(completedDate);
        expectedLoan.setTerm(10);
        expectedLoan.setProduct(new FixedProduct());


        List<Loan> actualListLoanList = loanReader.readCSVToList();

        assertAll(
                () -> assertThat(actualListLoanList.size()).isEqualTo(1),
                () -> assertThat(actualListLoanList.get(0)).isEqualToComparingFieldByFieldRecursively(expectedLoan)
        );

    }

    @Test
    void checkErrorThrown() {
        this.loanReader = new LoanReader("src/test/resources/non-existent.csv");

        assertThatThrownBy(() -> loanReader.readCSVToList()).isInstanceOf(FileNotFoundException.class);

    }
}