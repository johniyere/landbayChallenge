package com.landbay.challenge.readers;

import com.landbay.challenge.InvestmentRequest;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class InvestmentRequestReader extends CSVFileReader {

    public InvestmentRequestReader(String filename) {
        super(filename);
    }

    @Override
    public List<InvestmentRequest> readCSVToList() throws FileNotFoundException {
        return new CsvToBeanBuilder(new FileReader(this.fileName))
                .withType(InvestmentRequest.class).build().parse();
    }


}
