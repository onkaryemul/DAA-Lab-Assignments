package assignment3;

// Tower of Hanoi
/*
   2. Write a program to Tower of Hanoi Problem in recursive approach.
*/

public class Example2 {
    public static void towerOfHanoi(int n, char src, char aux, char dest) {
        // Base case
        if(n == 0) {
            return;
        }

        // Work to do
        towerOfHanoi(n-1, src, dest, aux); // recursively first move n-1 disks from src to aux using dest

        System.out.println("Move disk " + n + " from " + src + " to " + dest); // then move nth disk from src to dest

        towerOfHanoi(n-1, aux, src, dest); // finally, then recursively move n-1 disks from aux to dest using src
    }

    public static void main(String[] args) {
        int n = 3; // Number of disks
        towerOfHanoi(n, 'A', 'B', 'C');
    }

}
