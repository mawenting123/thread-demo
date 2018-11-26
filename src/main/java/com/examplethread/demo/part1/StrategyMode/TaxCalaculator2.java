package com.examplethread.demo.part1.StrategyMode;

/**
 * 策略模式--计算税费
 */
public class TaxCalaculator2 {
    private final double salary;
    private final double bonus;

    public CalaculatorStrategy calaculatorStrategy;

    public TaxCalaculator2(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    protected double calaTax(double salary, double bonus){
        return calaculatorStrategy.calaculate(salary,bonus);
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

    public CalaculatorStrategy getCalaculatorStrategy() {
        return calaculatorStrategy;
    }

    public void setCalaculatorStrategy(CalaculatorStrategy calaculatorStrategy) {
        this.calaculatorStrategy = calaculatorStrategy;
    }
}
