package com.examplethread.demo.StrategyMode;

public class SimpleCalaculatorStrategy implements CalaculatorStrategy {

    private static final double SALARY_RATE = 0.1;
    private static final double BONUS_RATE = 0.05;
    @Override
    public double calaculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
