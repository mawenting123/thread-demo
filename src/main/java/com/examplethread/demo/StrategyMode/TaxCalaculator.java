package com.examplethread.demo.StrategyMode;

/**
 * 策略模式--计算税费
 */
public class TaxCalaculator {
    private final double salary;
    private final double bonus;

    public TaxCalaculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    protected double calaTax(double salary, double bonus){

        return 0.0d;
    }

    public double calaculate(){
        return this.calaTax(salary,bonus);
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }
}
