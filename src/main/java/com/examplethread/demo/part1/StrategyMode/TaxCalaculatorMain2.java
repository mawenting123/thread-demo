package com.examplethread.demo.part1.StrategyMode;

public class TaxCalaculatorMain2 {
    public static void main(String[] args) {
        TaxCalaculator2 taxCalaculator2 = new TaxCalaculator2(10000d,100d);
        CalaculatorStrategy strategy = new SimpleCalaculatorStrategy();
        taxCalaculator2.setCalaculatorStrategy(strategy);
        double tax= taxCalaculator2.calaculate();
        System.out.println(tax);
    }
}
