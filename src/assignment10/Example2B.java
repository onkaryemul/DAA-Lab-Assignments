package assignment10;

/*
   2. Implement following problem using graph traversal Technique:
       b) Find Articulation Point in Graph using Depth First Search (DFS) and mention whether Graph is Biconnected or not.
*/

import java.util.ArrayList;
import java.util.List;

public class Example2B {
    private static int time = 0;
    private static boolean biconnected = true; // Add a flag to track biconnected status

    private static void dfs(List<Integer>[] adj, int V, int[] disc, int[] low, int[] parent, boolean[] articulation, int u) {
        int children = 0;
        disc[u] = low[u] = ++time;
        for (int v : adj[u]) {
            if (disc[v] == 0) {
                children++;
                parent[v] = u;
                dfs(adj, V, disc, low, parent, articulation, v);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] >= disc[u] && parent[u] != -1) {
                    articulation[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (parent[u] == -1 && children > 1) {
            articulation[u] = true;
        }

        // If any vertex is an articulation point, the graph is not biconnected
        if (articulation[u]) {
            biconnected = false;
        }
    }

    private static void findArticulationPoints(List<Integer>[] adj, int V) {
        int[] disc = new int[V + 1]; // Increase the size of the arrays
        int[] low = new int[V + 1];
        int[] parent = new int[V + 1];
        boolean[] articulation = new boolean[V + 1];

        for (int i = 1; i <= V; i++) { // Start vertex numbering from 1
            disc[i] = 0;
            low[i] = Integer.MAX_VALUE;
            parent[i] = -1;
            articulation[i] = false;
        }

        for (int i = 1; i <= V; i++) { // Iterate from 1 to V
            if (disc[i] == 0) {
                dfs(adj, V, disc, low, parent, articulation, i);
            }
        }

        System.out.println("Articulation points in the graph:");
        for (int i = 1; i <= V; i++) { // Start from vertex 1
            if (articulation[i]) {
                System.out.println(i);
            }
        }

        // Print whether the graph is biconnected
        if (biconnected) {
            System.out.println("The graph is biconnected.");
        } else {
            System.out.println("The graph is not biconnected.");
        }
    }

    private static void addEdge(List<Integer>[] adj, int u, int v) {
        adj[u].add(v);
        adj[v].add(u);
    }

    public static void main(String[] args) {
        int V = 5;
        List<Integer>[] adj = new List[V + 1];

        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        addEdge(adj, 1, 2);
        addEdge(adj, 1, 3);
        addEdge(adj, 2, 3);
        addEdge(adj, 3, 4);
        addEdge(adj, 4, 5);

        findArticulationPoints(adj, V);
    }
}
