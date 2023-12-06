package assignment7;

// Prim's Algorithm

import java.util.Arrays;

public class Example1B {
    static final int V = 10;

    public static int minKey(boolean[] mst, int[] key) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < V; i++) {
            if (!mst[i] && key[i] < min) {
                min = key[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    public static void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + " \t" + graph[i][parent[i]]);
        }
    }

    public static void minPrim(int[][] graph) {
        boolean[] mst = new boolean[V];
        int[] parent = new int[V];
        int[] key = new int[V];

        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(mst, false);

        key[0] = 0;
        parent[0] = -1;

        for (int i = 0; i < V - 1; i++) {
            int u = minKey(mst, key);
            mst[u] = true;

            for (int v = 0; v < V; v++) {
                if (!mst[v] && graph[u][v] != 0 && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        printMST(parent, graph);
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 15, 10, 19, 0, 0, 0, 0, 0, 0},
                {15, 0, 0, 7, 17, 0, 0, 0, 0, 0},
                {10, 0, 0, 16, 0, 14, 0, 0, 0, 0},
                {19, 7, 16, 0, 12, 6, 3, 0, 0, 0},
                {0, 17, 0, 12, 0, 0, 20, 13, 0, 0},
                {0, 0, 14, 6, 0, 0, 9, 0, 5, 0},
                {0, 0, 0, 3, 20, 9, 0, 4, 1, 11},
                {0, 0, 0, 0, 13, 0, 4, 0, 0, 2},
                {0, 0, 0, 0, 0, 5, 1, 0, 0, 18},
                {0, 0, 0, 0, 0, 0, 11, 2, 18, 0}
        };

        minPrim(graph);
    }
}
