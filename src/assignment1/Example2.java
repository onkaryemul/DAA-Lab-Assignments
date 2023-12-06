package assignment1;

// Sorting Algorithm
/*
   2. Write a method to sort an array of string so that all the anagrams are next to each other.
*/

import java.util.Arrays;
import java.util.Comparator;

class AnagramComparator implements Comparator<String> {
    private String sortString(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

    @Override
    public int compare(String str1, String str2) {
        return sortString(str1).compareTo(sortString(str2));
    }
}


public class Example2 {

    public static void sortAnagramsTogether(String[] arr) {
        Arrays.sort(arr, new AnagramComparator());
    }

    public static void main(String[] args) {
        String[] strings = {"listen", "elvis", "hello", "silent", "world", "act", "cat", "tar", "iceman", "cinema", "debitcard", "badcredit"};

        sortAnagramsTogether(strings);

        for(String str : strings) {
            System.out.println(str);
        }
    }

}
