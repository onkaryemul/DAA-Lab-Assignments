package assignment8;

/*
   1. From a given vertex in a weighted connected graph, implement shortest path finding Dijkstra's algorithm.
*/

import java.util.Arrays;

public class Example1 {
    private static final int V = 6;

    private int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    private void printSolution(int[] dist) {
        System.out.println("Vertex \t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t\t" + dist[i]);
        }
    }

    private void dijkstra(int[][] graph, int src) {
        int[] dist = new int[V];
        boolean[] sptSet = new boolean[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);

        dist[src] = 0;

        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;

            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE &&
                        dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        printSolution(dist);
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 2, 0, 0, 0},
                {1, 0, 1, 3, 2, 0},
                {2, 1, 0, 1, 2, 0},
                {0, 3, 1, 0, 4, 3},
                {0, 2, 2, 4, 0, 3},
                {0, 0, 0, 3, 3, 0}
        };

        Example1 dijkstra = new Example1();
        dijkstra.dijkstra(graph, 0);
    }
}
