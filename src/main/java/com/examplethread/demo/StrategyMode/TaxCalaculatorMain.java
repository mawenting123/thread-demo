package com.examplethread.demo.StrategyMode;

public class TaxCalaculatorMain {
    public static void main(String[] args) {
        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d,100d){
            @Override
            public double calaTax(double salary, double bonus) {
                return getSalary() * 0.1 + getBonus() * 0.05;
            }
        };
        double tax= taxCalaculator.calaculate();
        System.out.println(tax);
    }
}
