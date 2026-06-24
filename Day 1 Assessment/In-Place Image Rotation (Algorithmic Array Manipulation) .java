import java.util.Arrays;

public class ImageProcessor {
    public static void main(String[] args) {
        int[][] image = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        rotateImage(image);
        
        for (int[] row : image) {
            System.out.println(Arrays.toString(row));
        }
        // Expected Output:
        // [7, 4, 1]
        // [8, 5, 2]
        // [9, 6, 3]
    }

    public static void rotateImage(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix (swap matrix[i][j] with matrix[j][i])
        for (int i = 0; i < n; i++) {
            // Note: j starts at i to prevent double-swapping back to original
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row horizontally
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}