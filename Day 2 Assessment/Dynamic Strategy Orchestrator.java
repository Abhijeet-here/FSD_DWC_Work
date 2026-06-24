interface PricingStrategy {
    double calculate(double total);
}

class BlackFridayStrategy implements PricingStrategy {
    public double calculate(double total) { return total * 0.80; } // 20% off
}

class VIPStrategy implements PricingStrategy {
    public double calculate(double total) { return total - 50.0; } // $50 flat off
}

public class CheckoutEngine {
    public static void main(String[] args) {
        // Polymorphic Array: References in Stack pointing to different objects in Heap
        PricingStrategy[] discounts = { new BlackFridayStrategy(), new VIPStrategy() };
        
        double currentTotal = 500.0;
        for (PricingStrategy strategy : discounts) {
            // Late Binding dynamically triggers the correct math logic
            currentTotal = strategy.calculate(currentTotal);
        }
        System.out.println("Final Price: $" + currentTotal);
    }
}