package assignment1;

// Sorting Algorithm
/*
   1. You are given two sorted array, A and B, where A has a large enough buffer at the end to hold B.
      Write a method to merge B into A in sorted order.
*/

public class Example1 {

    public static void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1; // Index of last element in array A
        int j = n - 1; // Index of last element in array B
        int k = m + n - 1; // Index of last position in merged array A

        while(i>=0 && j>=0) {
            if(A[i] > B[j]) {
                A[k] = A[i];
                i--;
            } else {
                A[k] = B[j];
                j--;
            }
            k--;
        }

        // Copy any remaining elements from B into A
        while(j>=0) {
            A[k] = B[j];
            j--;
            k--;
        }
    }

    public static void main(String[] args) {
        int[] A = new int[10];  // Assuming A has a buffer to hold B
        A[0] = 1;
        A[1] = 3;
        A[2] = 5;

        int[] B = {2,4,6,8,10};

        merge(A, 3, B, 5);

        // Printing merged array A
        for(int num : A) {
            System.out.print(num + " ");
        }
    }

}

