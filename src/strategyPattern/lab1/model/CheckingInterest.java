package strategyPattern.lab1.model;

import strategyPattern.lab1.utils.InterestBehavior;

public class CheckingInterest implements InterestBehavior {

    @Override
    public double calculateInterest(double balance) {

            if(balance < 1000){
                return balance * 0.015;
            }
            return balance * 0.025;

    }
}
