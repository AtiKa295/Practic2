package org.example.service;

public class StepDiscountPolicy implements IDiscountPolicy {
    private final double initialDiscount;
    private final double discountStep;

    public StepDiscountPolicy(double initialDiscount, double discountStep) {
        this.initialDiscount = initialDiscount;
        this.discountStep = discountStep;
    }

    @Override
    public double calulateDiscount(int orderIndex) {
        return Math.max(0.0, initialDiscount - (orderIndex * discountStep));
    }
}
