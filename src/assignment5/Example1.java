package assignment5;

// Divide and Conquer Strategy

// Strassen's Matrix Multiplication
/*
   1) Implement Naive method to multiply two matrices and Justify complexity is O(n^3).
*/

public class Example1 {

    // function to multiply two matrices using Naive method
    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;

        int[][] result = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                result[i][j] = 0;
                for(int k=0; k<n; k++) {
                    result[i][j] += ( A[i][k] * B[k][j] );
                }
            }
        }

        return result;
    }

    // function to print matrix
    public static void printMatrix(int[][] matrix) {
        int n = matrix.length;

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static void main(String[] args) {
         int[][] A = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
         int[][] B = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

         int[][] C = multiply(A, B);

         System.out.println("Matrix A: ");
         printMatrix(A);

         System.out.println("Matrix B: ");
         printMatrix(B);

         System.out.println("Matrix C(result of multiplication): ");
         printMatrix(C);
    }

}
