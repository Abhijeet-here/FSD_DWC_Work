import java.util.Arrays;

public class SecurityWipe {
    public static void main(String[] args) {
        int[] sensitiveData = {482, 991, 304}; // Simulated sensitive payload
        
        // Attempt 1: The junior developer's approach
        wrongWipe(sensitiveData);
        System.out.println("After wrongWipe: " + Arrays.toString(sensitiveData)); 
        // Output: [482, 991, 304] -> DATA LEAKED!
        
        // Attempt 2: The correct approach
        rightWipe(sensitiveData);
        System.out.println("After rightWipe: " + Arrays.toString(sensitiveData)); 
        // Output: [0, 0, 0] -> DATA SECURED.
    }
    
    // TRAP: This only repoints the local 'arr' reference to a new array on the heap.
    // The original array in main() remains untouched.
    static void wrongWipe(int[] arr) {
        arr = new int[3]; 
    }
    
    // FIX: We use the local reference copy to traverse to the actual heap memory
    // and mutate the data in place.
    static void rightWipe(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0; 
        }
    }
}