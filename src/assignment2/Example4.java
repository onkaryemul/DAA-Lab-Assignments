package assignment2;

// Sorting Algorithm
/*
   4. Let A and B two arrays of n elements each. Given a number K, give an O(n*logn) time algorithm for determining whether
      there exists a belongs to A and b belongs to B such that a+b=K.
*/

import java.util.Arrays;

public class Example4 {

    public static boolean hasPairWithSum(int[] A, int[] B, int k) {
        Arrays.sort(A);
        Arrays.sort(B);

        int ptrA = 0;
        int ptrB = B.length - 1;

        while(ptrA < A.length && ptrB >= 0) {
            int sum = A[ptrA] + B[ptrB];

            if(sum == k) {
                return true;
            } else if (sum < k) {
                ptrA++;
            } else {
                ptrB--;
            }
        }

        return false;
    }


    public static void main(String[] args) {
         int[] A = {1, 2, 4, 7};
         int[] B = {3, 5, 8, 9};
         int k = 10;

         boolean result = hasPairWithSum(A, B, k);

         if(result) {
             System.out.println("A pair with sum " + k + " exists.");
         } else {
             System.out.println("No pair with sum " + k + " exists.");
         }
    }

}

