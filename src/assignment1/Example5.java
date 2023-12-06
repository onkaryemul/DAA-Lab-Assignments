package assignment1;

// Sorting Algorithm
/*
    5. Given a sorted array of string which is interspersed with empty string, write a method to find the location of a given string.
       EXAMPLE:
       Input: find "ball" in {"at", "", "", "ball", "", "", "car", "", "", "dad", "", ""}
       Output: 4
*/

public class Example5 {

    public static int search(String[] arr, String target) {
        if(arr == null || target == null || target.isEmpty()) {
            return -1;
        }

        int left = 0;
        int right = arr.length - 1;

        while(left <= right) {
            // Move mid to the nearest non-empty string
            while(left <= right && arr[right].isEmpty()) {
                right--;
            }
            if(left > right) {
                break;
            }

            int mid = left + (right-left)/2;

            // Move mid to the nearest non-empty string
            while(arr[mid].isEmpty()) {
                mid++;
            }

            int compareResult = arr[mid].compareTo(target);

            if(compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Element not found
    }

    public static void main(String[] args) {
        String[] sortedArray = {"at", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String target = "ball";

        int index = search(sortedArray, target);

        if( index != -1 ) {
            System.out.println("String '" + target + "' found at location " + (index+1));
        } else {
            System.out.println("String '" + target + "' not found in the array");
        }

    }

}

