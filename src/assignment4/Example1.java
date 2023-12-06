package assignment4;

// Divide and Conquer Strategy
/*
    1. Implement algorithm to Find the maximum element in an array which is first increasing and then decreasing with
       Time Complexity O(logn).
*/


public class Example1 {

    // function to find maximum element in an array which is first increasing and then decreasing
    // using modified binary search approach
    public static int findMax(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while(left < right) {
            int mid = left + (right - left)/2;

            if(arr[mid] < arr[mid+1]) {
                left = mid + 1; // we are in the increasing subarray, move to the right
            } else {
                right = mid; // We are in the decreasing subarray, move to the left
            }
        }

        return arr[left]; // at the end, left and right will point to the maximum element
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 8, 12, 4, 2};

        int max = findMax(arr);

        System.out.println("The maximum element is: " + max);
    }

}
