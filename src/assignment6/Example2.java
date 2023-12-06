package assignment6;

// Greedy Method
// To apply Greedy method to solve problems of:

/*
   2) To implement Fractional Knapsack problem for 3 objects (n=3).
          (w1,w2,w3)=(18,15,10)
          (p1,p2,p3)=(25,24,15)
          M=20
       With strategy:
       a) Largest-profit strategy
       b) Smallest-weight strategy
       c) Largest profit-weight ratio strategy
*/

import java.util.Arrays;

class Item implements Comparable<Item> {
    int weight;
    int profit;
    double profitWeightRatio;

    public Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
        this.profitWeightRatio = (double) profit / weight;
    }

    @Override
    public int compareTo(Item other) {
        // For largest-profit strategy, sort in descending order of profit
        return Double.compare(other.profit, this.profit);
    }
}

public class Example2 {
    public static double knapsackLargestProfit(Item[] items, int capacity) {
        Arrays.sort(items);
        double totalProfit = 0.0;
        int currentCapacity = capacity;

        for (Item item : items) {
            if (currentCapacity >= item.weight) {
                totalProfit += item.profit;
                currentCapacity -= item.weight;
            } else {
                totalProfit += item.profitWeightRatio * currentCapacity;
                break;
            }
        }

        return totalProfit;
    }

    public static double knapsackSmallestWeight(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> Double.compare(a.weight, b.weight));
        double totalProfit = 0.0;
        int currentCapacity = capacity;

        for (Item item : items) {
            if (currentCapacity >= item.weight) {
                totalProfit += item.profit;
                currentCapacity -= item.weight;
            } else {
                totalProfit += item.profitWeightRatio * currentCapacity;
                break;
            }
        }

        return totalProfit;
    }

    public static double knapsackLargestProfitWeightRatio(Item[] items, int capacity) {
        Arrays.sort(items, (a, b) -> Double.compare(b.profitWeightRatio, a.profitWeightRatio));
        double totalProfit = 0.0;
        int currentCapacity = capacity;

        for (Item item : items) {
            if (currentCapacity >= item.weight) {
                totalProfit += item.profit;
                currentCapacity -= item.weight;
            } else {
                totalProfit += item.profitWeightRatio * currentCapacity;
                break;
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {
        int n = 3;
        int capacity = 20;
        Item[] items = new Item[n];
        items[0] = new Item(18, 25);
        items[1] = new Item(15, 24);
        items[2] = new Item(10, 15);

        // Largest-profit strategy
        double largestProfit = knapsackLargestProfit(items, capacity);
        System.out.println("Largest-profit strategy: " + largestProfit);

        // Smallest-weight strategy
        double smallestWeight = knapsackSmallestWeight(items, capacity);
        System.out.println("Smallest-weight strategy: " + smallestWeight);

        // Largest profit-weight ratio strategy
        double largestProfitWeightRatio = knapsackLargestProfitWeightRatio(items, capacity);
        System.out.println("Largest profit-weight ratio strategy: " + largestProfitWeightRatio);
    }
}


