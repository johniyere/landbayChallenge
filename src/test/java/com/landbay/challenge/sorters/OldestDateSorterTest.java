package com.landbay.challenge.sorters;

import com.landbay.challenge.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OldestDateSorterTest {

    @Mock
    Loan loan1;

    @Mock
    Loan loan2;

    private OldestDateSorter oldestDateSorter;

    @BeforeEach
    void setUp() {
        oldestDateSorter = new OldestDateSorter();
    }

    @Nested
    class OrderMethod {

        @Test
        void loansSortedInDescendingOrder() {
            when(loan1.getCompletedDate()).thenReturn(new GregorianCalendar(2015, 00, 01).getTime());
            when(loan2.getCompletedDate()).thenReturn(new GregorianCalendar(2015, 00, 02).getTime());

            List<Loan> actualLoans = Stream.of(loan1, loan2)
                    .sorted(oldestDateSorter.order())
                    .collect(Collectors.toList());

            assertThat(actualLoans).containsExactly(loan1, loan2);
        }
    }


}