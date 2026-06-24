// 1. Strategy Interface
public interface PaymentStrategy {
    boolean processPayment(double amount);
}

// 2. Concrete Strategies
public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing $" + amount + " via Credit Card Gateway.");
        return true;
    }
}

public class CryptoStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing $" + amount + " via Blockchain Network.");
        return true;
    }
}

// 3. Context Class (Composition)
public class TransactionProcessor {
    // Strategy injected via composition
    private PaymentStrategy strategy;

    public TransactionProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    // Allows dynamic swapping at runtime (Open/Closed Principle)
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeTransaction(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("Payment strategy not initialized.");
        }
        boolean success = strategy.processPayment(amount);
        System.out.println("Transaction Status: " + (success ? "SUCCESS" : "FAILED"));
    }
}