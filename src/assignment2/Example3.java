package assignment2;

// Sorting Algorithm
/*
   3. Given an array A of n elements, each of which is an integer in the range [1,n^2]. How do we sort the array in O(n) time?
*/

// Count Sort
import java.util.*;

public class Example3 {

    // function to sort in O(n) time complexity with elements in the range [1,n^2]
    public static void linearSort(int[] A) {
        int n = A.length;

        int[] B = new int[n*n + 1]; // Create a temporary array B of size n^2 + 1

        // Count the occurrences of each element in A and store in B
        for(int x : A) {
            B[x]++;
        }

        int j = 0; // Initialize an index variable j to 0

        // Reconstruct the sorted array A
        for(int i=1; i<=n*n; i++) {
            while(B[i] > 0) {
                A[j] = i;
                j++;
                B[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] A = {4, 36, 2, 49, 3, 16, 1};
        System.out.println("Original Array: " + Arrays.toString(A));
        linearSort(A);
        System.out.println("Sorted Array: " + Arrays.toString(A));
    }

}
