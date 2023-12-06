package assignment6;

// 1. B) What is the solution generated by the function JS when n=7, (p1,p2,p3,...,p7)=(3,5,20,18,1,6,30), and (d1,d2,d3,...,d7)=(1,3,4,3,2,1,2) ?

import java.util.Arrays;

class Job {
    char id;
    int deadline;
    int profit;

    Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Example1B {
    public static void findJobSequence(Job[] jobs) {
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
        char[] jobIds = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        int[] profits = {3, 5, 20, 18, 1, 6, 30};
        int[] deadlines = {1, 3, 4, 3, 2, 1, 2};

        Job[] jobs = new Job[jobIds.length];
        for (int i = 0; i < jobIds.length; i++) {
            jobs[i] = new Job(jobIds[i], deadlines[i], profits[i]);
        }

        findJobSequence(jobs);
    }
}