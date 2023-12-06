package assignment2;

// Sorting Algorithm
/*
   2. Given an array A[0...n-1], where each element of the array represents a vote in the election. Assume that each vote is
      given as an integer representing the ID of the chosen candidate. Given an algorithm for determining who wins the election.
*/

// Java Implementation of the Boyer-Moore Majority Vote Algorithm:

public class Example2 {

    public static int findWinner(int[] votes) {
        int candidate = 0;
        int count = 0;

        for(int vote : votes) {
            if(count == 0) {
                candidate = vote;
                count = 1;
            } else if (candidate == vote) {
                count++;
            } else {
                count--;
            }
        }

        int candidateCount = 0;

        for(int vote : votes) {
            if(vote == candidate) {
                candidateCount++;
            }
        }

        if(candidateCount > votes.length / 2) {
            return candidate; // Candidate wins
        } else {
            return -1; // No clear winner
        }
    }


    public static void main(String[] args) {
        int[] votes = {3, 2, 3, 3, 4, 3, 5, 3};

        int winner = findWinner(votes);

        if(winner != -1) {
            System.out.println("Candidate " + winner + " wins the election.");
        } else {
            System.out.println("No clear winner in the election.");
        }
    }

}

