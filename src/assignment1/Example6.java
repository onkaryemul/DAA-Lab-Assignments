package assignment1;

// Sorting Algorithm
/*
    6. Given an M*N matrix in which each row and each column is sorted in ascending order, write a method to find an element.
*/

public class Example6 {

    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;  // Starting from the first row
        int col = cols - 1; // Starting from the last column

        while(row < rows && col >= 0) {
            int current = matrix[row][col];

            if(current == target) {
                return true;
            } else if (current < target ) {
                row++; // Move to the next row (downward)
            } else {
                col--; // Move to the previous column (leftward)
            }
        }

        return false; // Element not found
    }

    public static void main(String[] args) {
        int[][] matrix = {
                           {1, 4, 7, 11},
                           {2, 5, 8, 12},
                           {3, 6, 9, 16},
                           {10, 13, 14, 17}
                         };
        int target = 5;

        boolean found = searchMatrix(matrix, target);

        if(found) {
            System.out.println("Element " + target + " found in the matrix.");
        } else {
            System.out.println("Element " + target + " not found in the matrix.");
        }
    }

}

