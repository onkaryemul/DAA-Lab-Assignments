package assignment2;

// Sorting Algorithm
/*
   1. Given an array A[0...n-1] of n numbers containing repetition of some number. Given an algorithm for checking whether there
      are repeated element or not. Assume that we are not allowed to use additional space (i.e.,we can use a few temporary variable, O(1) storage)
*/


public class Example1 {

    public static boolean hasRepeatedElements(int[] A) {

        for(int i=0; i<A.length; i++) {
            if( A[Math.abs(A[i])] >= 0 ) {
                A[Math.abs(A[i])] = - A[Math.abs(A[i])];
            } else {
                return true; // Repeated element found
            }
        }

        return false; // No repeated element found
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 0, 2, 5, 3};

        boolean hasRepeats = hasRepeatedElements(arr);

        if(hasRepeats) {
            System.out.println("There are repeated elements in the array.");
        } else {
            System.out.println("There are no repeated elements in the array.");
        }
    }

}
