package com.landbay.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.landbay.challenge.enums.Funded;
import com.landbay.challenge.readers.InvestmentRequestReader;
import com.landbay.challenge.readers.LoanReader;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        final String LOAN_CSV_FILE = "src/main/resources/loans-data.csv";
        final String INVESTMENT_REQUEST_CSV_FILE = "src/main/resources/investmentRequests-data.csv";

        try {
            LoanReader loanReader = new LoanReader(LOAN_CSV_FILE);
            InvestmentRequestReader investmentRequestReader = new InvestmentRequestReader(INVESTMENT_REQUEST_CSV_FILE);
            Matcher matcher = new Matcher();
            ObjectMapper objectMapper = new ObjectMapper();

            List<Loan> loans = loanReader.readCSVToList();
            List<InvestmentRequest> investmentRequests = investmentRequestReader.readCSVToList();

            matcher.match(loans, investmentRequests);

            loans.stream()
                    .filter(loan -> loan.getFunded() == Funded.FULL)
                    .forEach(System.out::println);

            String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(loans);
            System.out.println(jsonString);

        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }


}
