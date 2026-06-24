public class TradingTruncation {
    public static void main(String[] args) {
        double transactionValue = 130.99;
        
        // Cast to int: Decimals are truncated, not rounded.
        int intValue = (int) transactionValue; 
        
        // Cast to byte: Exceeds the maximum byte value (127).
        byte byteValue = (byte) intValue;
        
        System.out.println("Original Double: " + transactionValue); // 130.99
        System.out.println("Truncated Int: " + intValue);           // 130
        System.out.println("Casted Byte: " + byteValue);            // -126
    }
}