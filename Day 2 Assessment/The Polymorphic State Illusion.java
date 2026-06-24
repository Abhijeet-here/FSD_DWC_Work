class BaseAccount {
    public double interestRate = 4.5;
    public double getInterestRate() { return 4.5; }
}

class PremiumAccount extends BaseAccount {
    public double interestRate = 7.5; // Hides parent variable
    
    @Override
    public double getInterestRate() { return 7.5; } // Overrides parent method
}

public class MemoryAnalysis {
    public static void main(String[] args) {
        BaseAccount account = new PremiumAccount(); // Upcasting
        
        // Output: 4.5. Early Binding (Compile-time resolution based on reference type).
        System.out.println(account.interestRate); 
        
        // Output: 7.5. Late Binding (Runtime resolution via vtable checking Heap object).
        System.out.println(account.getInterestRate()); 
    }
}