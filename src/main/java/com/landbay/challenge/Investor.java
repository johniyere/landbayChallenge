package com.landbay.challenge;

public class Investor {
    private String name;

    public Investor(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Investor (name=" + name +")";
    }
}
