public class VideoChunkBuffer {
    public static void main(String[] args) {
        int[] fibonacci = {1, 1, 2, 3, 5};
        
        // Initialize the jagged array with 5 rows
        int[][] chunkBuffer = new int[5][];
        
        int sequentialData = 1;
        int totalSum = 0;
        
        // Populate the buffer
        for (int i = 0; i < chunkBuffer.length; i++) {
            // Allocate the column size based on the Fibonacci sequence
            chunkBuffer[i] = new int[fibonacci[i]];
            
            // Iterate using the specific row's length to guarantee safety
            for (int j = 0; j < chunkBuffer[i].length; j++) {
                chunkBuffer[i][j] = sequentialData++;
                totalSum += chunkBuffer[i][j];
                System.out.print(chunkBuffer[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println("Total Sum of all chunks: " + totalSum);
    }
}