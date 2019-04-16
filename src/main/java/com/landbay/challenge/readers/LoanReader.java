package com.landbay.challenge.readers;

import com.landbay.challenge.Loan;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class LoanReader extends CSVFileReader {

    public LoanReader(String filename) {
        super(filename);
    }

    @Override
    public List<Loan> readCSVToList() throws FileNotFoundException {
        return new CsvToBeanBuilder(new FileReader(this.fileName))
                .withType(Loan.class).build().parse();
    }
}
