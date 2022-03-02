package strategyPattern.lab1.model;

import strategyPattern.lab1.utils.InterestBehavior;

public class SavingInterest implements InterestBehavior  {

    @Override
    public double calculateInterest(double balance) {

        if (balance < 1000) {
            return balance * 0.01;
        } else if (balance > 1000 && balance < 5000) {
            return balance * 0.02;
        }
            return balance * 0.04;
    }


}
