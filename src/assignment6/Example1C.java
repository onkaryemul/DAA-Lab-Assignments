package assignment6;

/*
    1. C) Input: Five Jobs with following deadlines and profits:

            JobID=(a,b,c,d,e)
            Deadline=(2,1,2,1,3)
            Profit=(100,19,27,25,15)

          Output: Following is maximum profit sequence of jobs:
                  c, a, e
*/

import java.util.Arrays;

class Job1 {
    char id;
    int deadline;
    int profit;

    Job1(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Example1C {
    public static void findJobSequence(Job1[] jobs) {
        int n = jobs.length;
        Arrays.sort(jobs, (a, b) -> Integer.compare(b.profit, a.profit)); // Sort jobs by profit in descending order

        boolean[] slots = new boolean[n];
        char[] result = new char[n];
        int totalProfit = 0;

        for (int i = 0; i < n; i++) {
            for (int j = Math.min(n, jobs[i].deadline) - 1; j >= 0; j--) {
                if (!slots[j]) {
                    slots[j] = true;
                    result[j] = jobs[i].id;
                    totalProfit += jobs[i].profit;
                    break;
                }
            }
        }

        System.out.println("Job Sequence: " + new String(result));
        System.out.println("Total Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        char[] jobIds = { 'a', 'b', 'c', 'd', 'e' };
        int[] profits = { 100, 19, 27, 25, 15 };
        int[] deadlines = { 2, 1, 2, 1, 3 };

        Job1[] jobs = new Job1[jobIds.length];
        for (int i = 0; i < jobIds.length; i++) {
            jobs[i] = new Job1(jobIds[i], deadlines[i], profits[i]);
        }

        findJobSequence(jobs);
    }
}
