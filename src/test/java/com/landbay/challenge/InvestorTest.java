package com.landbay.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvestorTest {
    Investor investor;

    @BeforeEach
    void setUp() {
        investor = new Investor("John");
    }

    @Test
    void getName_returnsCorrectName() {
        assertEquals("John", investor.getName());
    }

    @Test
    void toString_returnsObjectInStringForm() {
        assertEquals("Investor (name=John)", investor.toString());
    }
}