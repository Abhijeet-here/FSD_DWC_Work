public class CacheMemoryManager {
    public static void main(String[] args) {
        // Step 1: Allocate three distinct array payloads on the heap
        int[] bufferA = new int[1000];
        int[] bufferB = new int[1000];
        int[] bufferC = new int[1000];
        
        // Step 2: Intersect references. A 2D array acts as a master cache.
        int[][] masterCache = new int[2][];
        masterCache[0] = bufferA;
        masterCache[1] = bufferB;
        
        // Step 3: Begin severing references
        
        bufferA = null; 
        // PREDICTION: 'bufferA' payload is NOT eligible for GC.
        // Reason: 'masterCache[0]' still holds a strong reference to it.
        
        bufferC = null;
        // PREDICTION: 'bufferC' payload IS NOW eligible for GC.
        // Reason: The only reference pointing to it was the local variable 'bufferC'. 
        // That link is now severed.
        
        masterCache[1] = null;
        // PREDICTION: 'bufferB' payload is NOT eligible for GC yet.
        // Reason: We severed the cache's link, but the local variable 'bufferB' 
        // is still pointing to it in the method stack.
        
        masterCache = null;
        // PREDICTION: 'masterCache' 2D array payload AND the original 'bufferA' payload 
        // ARE NOW eligible for GC. 
        // Reason: The 2D array is detached from the root. Since it was the last 
        // thing holding 'bufferA', 'bufferA' goes down with the ship.
        
        bufferB = null;
        // PREDICTION: 'bufferB' payload IS NOW eligible for GC.
        // Reason: All links to this memory allocation are totally severed.
    }
}