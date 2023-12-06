package assignment10;

/*
   2. Implement following problem using graph traversal Technique:
       a) Check whether a graph is Bipartite or not using Breadth First Search (BFS)
*/

import java.util.LinkedList;
import java.util.Queue;

public class Example2A {
    final static int V = 4;

    public boolean isBipartite(int G[][], int src) {
        int[] colorArr = new int[V];
        for (int i = 0; i < V; i++) {
            colorArr[i] = -1;
        }

        colorArr[src] = 1;

        Queue<Integer> q = new LinkedList<>();
        q.add(src);

        while (!q.isEmpty()) {
            int u = q.poll();

            if (G[u][u] == 1) {
                return false;
            }

            for (int v = 0; v < V; v++) {
                if (G[u][v] == 1 && colorArr[v] == -1) {
                    colorArr[v] = 1 - colorArr[u];
                    q.add(v);
                } else if (G[u][v] == 1 && colorArr[v] == colorArr[u]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int G[][] = {
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0}
        };

        Example2A bg = new Example2A();
        System.out.println("RESULT:");
        System.out.println(bg.isBipartite(G, 0) ? "Yes" : "No");
    }
}

