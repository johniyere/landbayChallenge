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
    void testReturnsExpectedInvestorName() {
        assertEquals("John", investor.getName());
    }

    @Test
    void testReturnsExpectedInvestorObjectString() {
        assertEquals("Investor (name=John)", investor.toString());
    }
}