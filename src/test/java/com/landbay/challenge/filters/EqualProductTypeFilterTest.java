package com.landbay.challenge.filters;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Loan;
import com.landbay.challenge.enums.ProductType;
import com.landbay.challenge.products.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EqualProductTypeFilterTest {

    @InjectMocks
    Loan loan;

    @Mock
    Product loanProduct;

    @Mock
    InvestmentRequest investmentRequest;


    private EqualProductTypeFilter equalProductTypeFilter;

    @BeforeEach
    void setUp() {
        this.equalProductTypeFilter = new EqualProductTypeFilter();
    }

    @Nested
    class TestMethod {
        @Test
        void filterInvestmentRequestWhenProductTypesAreEqual() {
            when(loanProduct.getType()).thenReturn(ProductType.FIXED);
            when(investmentRequest.getProductType()).thenReturn(ProductType.FIXED);

            List<InvestmentRequest> actualInvestmentRequests = Stream.of(investmentRequest)
                    .filter(equalProductTypeFilter.test(loan))
                    .collect(Collectors.toList());


            assertThat(actualInvestmentRequests).isNotEmpty();

        }

        @Test
        void notFilterInvestmentRequestWhenProductTypesAreNotEqual() {
            when(loanProduct.getType()).thenReturn(ProductType.TRACKER);
            when(investmentRequest.getProductType()).thenReturn(ProductType.FIXED);

            List<InvestmentRequest> actualInvestmentRequests = Stream.of(investmentRequest)
                    .filter(equalProductTypeFilter.test(loan))
                    .collect(Collectors.toList());


            assertThat(actualInvestmentRequests).isEmpty();

        }
    }


}