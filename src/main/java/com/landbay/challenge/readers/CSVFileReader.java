package com.landbay.challenge.readers;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class CSVFileReader {
    protected String fileName;

    public CSVFileReader(String fileName) {
        this.fileName = fileName;
    }

    public abstract List readCSVToList() throws FileNotFoundException;

}
