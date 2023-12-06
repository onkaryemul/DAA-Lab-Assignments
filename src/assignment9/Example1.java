package assignment9;

/*
   1) From a given vertex in a weighted connected graph, Implement shortest path finding Bellman-Ford algorithm.
*/

import java.util.Arrays;

public class Example1 {
    static void BellmanFord(int[][] graph, int V, int E, int src) {
        int[] dis = new int[V];
        Arrays.fill(dis, Integer.MAX_VALUE);

        dis[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < E; j++) {
                if (dis[graph[j][0]] != Integer.MAX_VALUE && dis[graph[j][0]] + graph[j][2] < dis[graph[j][1]]) {
                    dis[graph[j][1]] = dis[graph[j][0]] + graph[j][2];
                }
            }
        }

        for (int i = 0; i < E; i++) {
            int x = graph[i][0];
            int y = graph[i][1];
            int weight = graph[i][2];
            if (dis[x] != Integer.MAX_VALUE && dis[x] + weight < dis[y]) {
                System.out.println("Graph contains negative weight cycle");
                return;
            }
        }

        System.out.println("Vertex | Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t\t\t" + dis[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5;
        int E = 10;

        // Every edge has three values (u, v, w) where
        // the edge is from vertex u to v. And weight
        // of the edge is w.

        // s t x y z
        // 0 1 2 3 4

        // Initial Vertex S --> 0
        int[][] graph = {
                {0, 1, 6}, {0, 3, 7}, {1, 3, 8}, {1, 4, -4},
                {1, 2, 5}, {2, 1, -2}, {3, 2, -3}, {3, 4, 9},
                {4, 2, 7}, {4, 0, 2}
        };

        BellmanFord(graph, V, E, 0);
    }
}
