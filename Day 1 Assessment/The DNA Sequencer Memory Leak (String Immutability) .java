/**
 * Highly memory-efficient DNA Sequencer.
 * Utilizes a pre-allocated StringBuilder to prevent String Pool bloat 
 * and GC overhead during massive sequence ingestions.
 */
public class DNASequencer {
    
    // Internal buffer for O(1) amortized appends and in-place mutations
    private StringBuilder sequenceBuffer;

    /**
     * Constructor initializes the buffer with a strict capacity.
     * Prevents the underlying char[] from constantly resizing (which costs O(N) memory/time).
     * * @param initialCapacity The expected size of the sequence (e.g., 100000)
     */
    public DNASequencer(int initialCapacity) {
        // SPEED TO MARKET: Pre-allocating memory avoids costly array copy operations during growth.
        this.sequenceBuffer = new StringBuilder(initialCapacity);
    }

    /**
     * Efficiently appends raw sensor data to the sequence.
     * Time Complexity: O(K) where K is the length of sensorData.
     * Space Complexity: O(1) beyond the buffer capacity.
     * * @param sensorData Raw character array from the DNA sensor.
     */
    public void ingestSequence(char[] sensorData) {
        if (sensorData == null || sensorData.length == 0) return;
        
        // Appends directly to the existing memory block without creating new String objects.
        this.sequenceBuffer.append(sensorData);
    }

    /**
     * Mutates the DNA by finding a target sequence and replacing it in-place.
     * Time Complexity: O(N * M) for indexOf, plus O(N) for replace shift operations.
     * Space Complexity: O(1) auxiliary space (mutation is in-place).
     * * @param target The sequence to find.
     * @param replacement The sequence to inject.
     */
    public void mutateDNA(String target, String replacement) {
        if (target == null || target.isEmpty() || replacement == null) return;

        // Locate the first occurrence of the target sequence
        int startIndex = this.sequenceBuffer.indexOf(target);
        
        if (startIndex != -1) {
            int endIndex = startIndex + target.length();
            
            // In-place mutation. Does NOT instantiate a new String object for the main sequence.
            this.sequenceBuffer.replace(startIndex, endIndex, replacement);
        }
    }

    /**
     * Returns the finalized sequence.
     */
    public String getFinalSequence() {
        return this.sequenceBuffer.toString();
    }
}