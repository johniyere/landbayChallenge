package com.landbay.challenge.readers;

import com.landbay.challenge.InvestmentRequest;
import com.landbay.challenge.Investor;
import com.landbay.challenge.enums.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InvestmentRequestReaderTest {

    private InvestmentRequestReader investmentRequestReader;

    @BeforeEach
    void setUp() {

    }
    @Test
    void testShouldCreateInvestmentRequestsListFromFileRead() throws FileNotFoundException {
        this.investmentRequestReader = new InvestmentRequestReader("src/test/resources/investmentRequests-test-data.csv");
        InvestmentRequest expectedInvestmentRequest = new InvestmentRequest();

        expectedInvestmentRequest.setInvestor(new Investor("Bob"));
        expectedInvestmentRequest.setTerm(30);
        expectedInvestmentRequest.setInvestmentAmount(330000);
        expectedInvestmentRequest.setProductType(ProductType.TRACKER);

        List<InvestmentRequest> expectedInvestmentRequestList = new ArrayList<>();
        expectedInvestmentRequestList.add(expectedInvestmentRequest);

        List<InvestmentRequest> actualInvestmentRequestList = investmentRequestReader.readCSVToList();


        assertThat(actualInvestmentRequestList).isNotEmpty();
        assertThat(actualInvestmentRequestList.size()).isEqualTo(1);
        assertThat(expectedInvestmentRequest).isEqualToComparingFieldByFieldRecursively(actualInvestmentRequestList.get(0));
    }

    @Test
    void testNonExistentFileShouldThrowFileNotFoundException() {
        this.investmentRequestReader = new InvestmentRequestReader("src/test/resources/non-exist.csv");

        assertThatThrownBy(() -> investmentRequestReader.readCSVToList()).isInstanceOf(FileNotFoundException.class);

    }
}