package assignment6;

//  1. D) Study and implement Disjoint set algorithm to reduce time complexity of JS from O(n^2) to nearly O(n).

import java.util.Arrays;

class Job2 {
    char id;
    int deadline, profit;

    Job2(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

class DisjointSet {
    int[] parent;

    DisjointSet(int n) {
        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    int find(int s) {
        if (s == parent[s])
            return s;
        return parent[s] = find(parent[s]);
    }

    void merge(int u, int v) {
        parent[v] = u;
    }
}

public class Example1D {
    static boolean cmp(Job2 a, Job2 b) {
        return a.profit > b.profit;
    }

    static int findMaxDeadline(Job2[] arr, int n) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, arr[i].deadline);
        }
        return ans;
    }

    static void printJobScheduling(Job2[] arr, int n) {
        Arrays.sort(arr, (a, b) -> Integer.compare(b.profit, a.profit));

        int maxDeadline = findMaxDeadline(arr, n);
        DisjointSet ds = new DisjointSet(maxDeadline);

        for (int i = 0; i < n; i++) {
            int availableSlot = ds.find(arr[i].deadline);

            if (availableSlot > 0) {
                ds.merge(ds.find(availableSlot - 1), availableSlot);
                System.out.print(arr[i].id + " ");
            }
        }
    }

    public static void main(String[] args) {
        Job2[] arr = {
                new Job2('a', 2, 100),
                new Job2('b', 1, 19),
                new Job2('c', 2, 27),
                new Job2('d', 1, 25),
                new Job2('e', 3, 15)
        };
        int n = arr.length;

        System.out.println("Following jobs need to be executed for maximum profit:");
        printJobScheduling(arr, n);
    }
}

