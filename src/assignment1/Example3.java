package assignment1;

// Sorting Algorithm
/*
   3. Given a sorted array of n integers that has been rotated an unknown number of times, write code to find an element in an array.
      You may assume that the array was originally sorted in increasing order.
      EXAMPLE:
      Input: find 5 in {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
      Output: 8(the index of 5 in the array)
*/

public class Example3 {

    public static int search(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while(left <= right) {
            int mid = left + (right-left)/2;

            if(arr[mid] == target) {
                return mid;
            }

            if(arr[left] <= arr[mid]) { // Left half is sorted
                if(arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // Right half is sorted
                if(arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1; // Element not found
    }

    public static void main(String[] args) {
        int[] rotatedArray = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        int target = 5;

        int index = search(rotatedArray, target);

        if(index != -1) {
            System.out.println("Element " + target + " found at index " + index);
        } else {
            System.out.println("Element " + target + " not found in the array");
        }

    }

}

